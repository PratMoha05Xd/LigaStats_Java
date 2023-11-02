
package com.mycompany.estadisticasliga;

/**
 *
 * @author ignac
 */
public class Partido {

    //Date,Time,HomeTeam,AwayTeam,FTHG,FTAG,FTR,HTHG,HTAG,HTR,HS,AS,HST,AST,HF,AF,HC,AC,HY,AY,HR,AR
    private String fecha;
    private String eq_local;
    private String eq_visitante;
    private int total_goles_local;
    private int total_goles_visitante;
    private int primera_parte_goles_local;
    private int primera_parte_goles_visitante;
    private int tiros_puerta_local;
    private int tiros_puerta_visitante;
    private int corners_local;
    private int corners_visitante;

    //cronstructoras
    public Partido(String fecha_, String eq_local_,
                   String eq_visitante_, int total_goles_local_,
                   int total_goles_visitante_, int primera_parte_goles_local_,
                   int primera_parte_goles_visitante_, int tiros_puerta_local_,
                   int tiros_puerta_visitante_, int corners_local_, int corners_visitante_
    ){
        this.fecha=fecha_;
        this.eq_local=eq_local_;
        this.eq_visitante=eq_visitante_;
        this.total_goles_local=total_goles_local_;
        this.total_goles_visitante=total_goles_visitante_;
        this.primera_parte_goles_local=primera_parte_goles_local_;
        this.primera_parte_goles_visitante=primera_parte_goles_visitante_;
        this.tiros_puerta_local=tiros_puerta_local_;
        this.tiros_puerta_visitante=tiros_puerta_visitante_;
        this.corners_local=corners_local_;
        this.corners_visitante=corners_visitante_;
    }

    //mostrar por consola los datos del partido
    public void imprimir_datos_partido(){
        System.out.println(fecha+"|"+eq_local+"|"+eq_visitante);
    }

    //devuelve los goles que ha marcado el eq local
    public int get_total_goles_equipo_local(){
        return total_goles_local;
    }

    //devuelve los goles que ha marcado el eq visitante
    public int get_total_goles_equipo_visitante(){
        return total_goles_visitante;
    }

    //devuelve el nombre del equipo local
    public String get_nombe_equipo_local(){
        return eq_local;
    }

    //devuelve el nombre del equipo local
    public String get_nombe_equipo_visitante(){
        return eq_visitante;
    }


}