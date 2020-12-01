package DataBase;

public class Cita {

    private int id;
    private String fecha_hora;
    private String motivo;
    private String paciente;
    private String doctor;
    private String resolucion;

    public Cita(int id, String fecha_hora, String motivo, String paciente, String doctor, String resolucion) {
        this.id = id;
        this.fecha_hora = fecha_hora;
        this.motivo = motivo;
        this.paciente = paciente;
        this.doctor = doctor;
        this.resolucion = resolucion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }



    public Cita(int id, String fecha_hora, String motivo, String paciente, String doctor) {
        this.id = id;
        this.fecha_hora = fecha_hora;
        this.motivo = motivo;
        this.paciente = paciente;
        this.doctor = doctor;
    }

    public Cita(String fecha_hora, String motivo, String paciente, String doctor) {
        this.fecha_hora = fecha_hora;
        this.motivo = motivo;
        this.paciente = paciente;
        this.doctor = doctor;
    }
    public Cita(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
