

package com.mycompany.estadisticasliga;

/**
 *
 * @author ignac
 */
public class EstadisticasLiga {

    public static void main(String[] args) {
        Temporada temporada=new Temporada("2022", "D:\\DAM2\\MP05\\UF01\\Java_OOP_P01\\src\\main\\java\\com\\mycompany\\estadisticasliga\\SP1.csv");
        temporada.calcular_clasificacion();
        temporada.imprime_clasificacion();
        /*temporada.imprimir_partidos();
        System.out.println("Media de goles que mete el equipo local: "+
                temporada.media_goles_equipos_locales());
        System.out.println("Media de goles que mete el Betis como local: "+
                temporada.media_goles_equipos_locales("Betis"));
        System.out.println("Media de goles que mete el Betis como visitante: "+
                temporada.media_goles_equipos_visitantes("Betis"));

        System.out.println("Media de goles que recebe el Betis es: "+
                temporada.calcula_media_de_goles_recibidos("Betis"));*/
        //temporada.diccionario_partidos_ganados();
        //temporada.imprimir_diccionario();
    }
}
