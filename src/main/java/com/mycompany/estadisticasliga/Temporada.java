/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estadisticasliga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ignac
 */
public class Temporada {

    //la key es el nombre del equipo
    //el valor es el número de partidos ganados
    private Map<String, Integer> mapa_ganados = new HashMap<>();
    private Map<String, int[]> clasificacion = new HashMap<>();
    private ArrayList<Partido> partidos=new ArrayList<>();
    private String year;
    private String fichero_datos;

    public Temporada(String year_, String fichero_datos_){
        this.year=year_;
        this.fichero_datos=fichero_datos_;
        cargar_datos();
    }


    //Se encarga de rellenar los datos del diccionario clasificacion, el diccionario clasificación
    //tiene como clave el nombre del equipo y como valor un vector de enteros de 3 posiciones.
    //la posición 0 del vector contiene el número de goles marcados por el equipo
    //la posición 1 contiene el número de goles que ha recibido el equipo
    //la posición 2 contiene los puntos que tiene el equipo en la liga, Victoria +3 puntos, Empate +1punto
    public void calcular_clasificacion() {
        for (Partido partido : partidos) {
            String equipoLocal = partido.get_nombe_equipo_local();
            String equipoVisitante = partido.get_nombe_equipo_visitante();
            int golesLocal = partido.get_total_goles_equipo_local();
            int golesVisitante = partido.get_total_goles_equipo_visitante();

            int[] estadisticasLocal = clasificacion.get(equipoLocal);
            if (estadisticasLocal == null) {
                estadisticasLocal = new int[3];
            }
            estadisticasLocal[0] += golesLocal;
            estadisticasLocal[1] += golesVisitante;

            if (golesLocal > golesVisitante) {
                estadisticasLocal[2] += 3;
            } else if (golesLocal == golesVisitante) {
                estadisticasLocal[2] += 1;
            }

            clasificacion.put(equipoLocal, estadisticasLocal);

            int[] estadisticasVisitante = clasificacion.get(equipoVisitante);
            if (estadisticasVisitante == null) {
                estadisticasVisitante = new int[3];
            }
            estadisticasVisitante[0] += golesVisitante;
            estadisticasVisitante[1] += golesLocal;

            if (golesVisitante > golesLocal) {
                estadisticasVisitante[2] += 3;
            } else if (golesVisitante == golesLocal) {
                estadisticasVisitante[2] += 1;
            }

            clasificacion.put(equipoVisitante, estadisticasVisitante);
        }
    }


    public void imprime_clasificacion() {
        List<Map.Entry<String, int[]>> listaClasificacion = new ArrayList<>(clasificacion.entrySet());
        listaClasificacion.sort((entry1, entry2) -> Integer.compare(entry2.getValue()[2], entry1.getValue()[2]));


        System.out.println("Clasificación:");
        System.out.println("Equipo  |  GM  |  GR |  P");
        for (Map.Entry<String, int[]> entry : listaClasificacion) {
            String equipo = entry.getKey();
            int[] estadisticas = entry.getValue();
            System.out.println(equipo + " | " + estadisticas[0] +
                    " | " + estadisticas[1] +
                    " | " + estadisticas[2]);
        }
    }

    public void imprimir_diccionario() {
        for (Map.Entry<String, Integer> entrada : mapa_ganados.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }

    //rellenar el diccionario mapa_ganados con el número
    //de partidos que ha ganado cada equipo
    public void diccionario_partidos_ganados(){
        for(int i=0; i<partidos.size(); i++){
            //nombre del equipo local
            String eq_local=partidos.get(i).get_nombe_equipo_local();
            //nombre del equipo visitante
            String eq_visitante=partidos.get(i).get_nombe_equipo_visitante();
            //goles del local
            int goles_local=partidos.get(i).get_total_goles_equipo_local();
            //goles del visitante
            int goles_visitante=partidos.get(i).get_total_goles_equipo_visitante();

            if(goles_local>goles_visitante){
                if(!mapa_ganados.containsKey(eq_local)){
                    mapa_ganados.put(eq_local, 1);
                }else{
                    int valor=mapa_ganados.get(eq_local)+1;
                    mapa_ganados.put(eq_local, valor);
                }
            }else if(goles_visitante>goles_local){
                if(!mapa_ganados.containsKey(eq_visitante)){
                    mapa_ganados.put(eq_visitante, 1);
                }else{
                    int valor=mapa_ganados.get(eq_visitante)+1;
                    mapa_ganados.put(eq_visitante, valor);
                }
            }
        }
    }

    //meter dentro de partidos, todos los partidos de
    //la temporada que leamos en el fichero_datos
    private void cargar_datos(){
        ArrayList<String[]> datos = new ArrayList<>();

        //Con BufferedReader leemos cualquier tipo de fichero
        //línea a línea
        try (BufferedReader br = new BufferedReader(new FileReader(fichero_datos))) {
            String linea;
            br.readLine(); //cabecera que no procesamos
            linea=br.readLine();
            //si linea es null es que hemos leido todas
            //las líneas del fichero
            while (linea != null) {
                // Suponiendo que las comas son los separadores de los datos
                // y no están dentro de los campos del CSV
                String[] campos = linea.split(",");
                datos.add(campos);
                //avanzamos a la siguiente linea
                linea=br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //ya tenemos todos los datos del fichero -excepto cabecera-
        //en la variable datos. Recorremos el ArrayList y para cada
        //posición del ArrayList instanciamos un partido
        for(int i=0; i<datos.size(); i++){
            Partido p=new Partido(datos.get(i)[1],
                    datos.get(i)[3],
                    datos.get(i)[4],
                    Integer.parseInt(datos.get(i)[5]),
                    Integer.parseInt(datos.get(i)[6]),
                    Integer.parseInt(datos.get(i)[8]),
                    Integer.parseInt(datos.get(i)[9]),
                    Integer.parseInt(datos.get(i)[11]),
                    Integer.parseInt(datos.get(i)[12]),
                    Integer.parseInt(datos.get(i)[17]),
                    Integer.parseInt(datos.get(i)[18])
            );
            partidos.add(p);
        }
    }

    //imprime los datos por consola de todos los partidos
    public void imprimir_partidos(){
        for(int i=0; i<partidos.size(); i++){
            partidos.get(i).imprimir_datos_partido();
        }
    }

    //devuelve un double que es la media de goles que
    //meten los equipos locales
    public double media_goles_equipos_locales(){
        double total_goles=0;
        for(int i=0; i<partidos.size(); i++){
            total_goles+=partidos.get(i).get_total_goles_equipo_local();
            //total_goles=total_goles+get_total_goles_equipo_local()
        }
        //aquí ya tenemos el total de los goles en total_goles
        //devolvemos la media
        return total_goles/partidos.size();
    }

    //Calcula la media de goles que ha hecho un equipo
    //jugando como local
    public double media_goles_equipos_locales(String equipo){
        double total_goles=0;
        double numero_partidos=0;
        for(int i=0; i<partidos.size(); i++){
            if(partidos.get(i).get_nombe_equipo_local().equals(equipo)){
                numero_partidos+=1;
                total_goles+=partidos.get(i).get_total_goles_equipo_local();
            }
        }
        //aquí en total_goles tendremos el total de goles locales de "equipo"
        return total_goles/numero_partidos;
    }

    public double media_goles_equipos_visitantes(String equipo){
        double total_goles=0;
        double numero_partidos=0;
        for(int i=0; i<partidos.size(); i++){
            if(partidos.get(i).get_nombe_equipo_visitante().equals(equipo)){
                numero_partidos+=1;
                total_goles+=partidos.get(i).get_total_goles_equipo_local();
            }
        }
        //aquí en total_goles tendremos el total de goles locales de "equipo"
        return total_goles/numero_partidos;
    }

    public double calcula_media_de_goles_recibidos(String equipo) {
        double numero_partidos=0;
        double total_goles_recibidos=0;
        for(int i=0; i<partidos.size(); i++){
            //equipo juega como local
            if(partidos.get(i).get_nombe_equipo_local()
                    .equals(equipo)){
                numero_partidos+=1;
                total_goles_recibidos+=partidos.get(i)
                        .get_total_goles_equipo_visitante();

            }
            //equipo juega como visitante
            if(partidos.get(i).get_nombe_equipo_visitante()
                    .equals(equipo)){
                numero_partidos+=1;
                total_goles_recibidos+=partidos.get(i)
                        .get_total_goles_equipo_local();
            }

        }
        return total_goles_recibidos/numero_partidos;
    }
}
