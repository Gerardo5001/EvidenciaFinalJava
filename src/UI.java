import DataBase.*;
import com.sun.org.apache.bcel.internal.generic.DCMPG;
import sun.nio.cs.US_ASCII;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UI extends JFrame implements ActionListener, ChangeListener {

    private ControlBD bd = new ControlBD();
    private Font fuenteTitulo=new Font("Dialog", Font.BOLD, 40);
    private Font fuenteOpcioones=new Font("Dialog", Font.BOLD, 20);
    private JPanel window;
    private String usuario;
    private String doctor;
    private HashMap<String,String> mapaMotivosCitas = new HashMap<>();
    private HashMap<String,Integer> mapaIndexCitas = new HashMap<>();
    //Objetos para UI login
    private JLabel et_titulo;
    private JButton btn_cerrarsesion;
    private JLabel et_usuario;
    private JTextArea ta_usuario;
    private JLabel et_password;
    private JTextArea ta_password;
    private JButton btn_acceder;
    //Objetos para UI paciente
    private JLabel et_motivo;
    private JTextArea ta_motivo;
    private JLabel et_fecha;
    private JLabel et_hora;
    private JTextField ta_dia;
    private JLabel et_separadorfecha1;
    private JLabel et_separadorfecha2;
    private JLabel et_separadorHora;
    private JTextField ta_mes;
    private JTextField ta_year;
    private JTextField ta_hora;
    private JTextField ta_minutos;
    private JSpinner spinnerDoctores;
    private JLabel et_doctor;
    private JButton btn_crearcita;
    //Objetos para UI doctor
    private JLabel et_citas;
    private JSpinner spinnerRegistroCitas;
    private JLabel et_resolcion;
    private JTextArea ta_resolucion;
    private JButton btn_guardarResolucion;
    //Objetos para UI administrador
    private JButton btn_cambiarAdmi;
    private JButton btn_borrarDoctor;
    private JTextArea ta_doctor;
    private JLabel et_especialidad;
    private JTextArea ta_especialidad;
    private JButton btn_agregarDoctor;
    private JLabel et_paciente;
    private SpinnerListModel pacienteModel = new SpinnerListModel(regresaPacientes());
    private JSpinner spinnerPacientes;
    private JButton btn_borrarPaciente;
    private JLabel et_nombre;
    private JTextArea ta_nombre;
    private JButton btn_agregarPaciente;
    private JLabel et_contraDoctor;
    private JTextArea ta_contraDoctor;
    private JLabel et_contraPaciente;
    private JTextArea ta_contraPaciente;

    public static void main(String [] args) throws IOException {
        ControlBD BD = new ControlBD();
        BD.validaDirectorio();  //Se valida que existan los archivos de no ser asi se crean junto con su directorio
        UI frame = new UI();    //Se abre la IU
        frame.mostrarLogin();

    }

    public UI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        window = new JPanel();
        window.setLayout(null);
        //Incio UI login
        et_titulo = new JLabel();
        window.add(et_titulo);

        et_usuario = new JLabel();
        window.add(et_usuario);

        ta_usuario = new JTextArea();
        window.add(ta_usuario);

        et_password = new JLabel();
        window.add(et_password);

        ta_password = new JTextArea();
        window.add(ta_password);

        btn_acceder = new JButton();
        window.add(btn_acceder);
        //Fin UI login
        //Inicio UI paciente
        et_motivo = new JLabel();
        et_motivo.setVisible(false);
        window.add(et_motivo);

        ta_motivo = new JTextArea();
        ta_motivo.setVisible(false);
        window.add(ta_motivo);

        et_fecha = new JLabel();
        et_fecha.setVisible(false);
        window.add(et_fecha);

        ta_dia = new JTextField();
        ta_dia.setVisible(false);
        window.add(ta_dia);

        et_separadorfecha1 = new JLabel();
        et_separadorfecha1.setVisible(false);
        window.add(et_separadorfecha1);

        ta_mes = new JTextField();
        ta_mes.setVisible(false);
        window.add(ta_mes);

        et_separadorfecha2 = new JLabel();
        et_separadorfecha2.setVisible(false);
        window.add(et_separadorfecha2);

        ta_year = new JTextField();
        ta_year.setVisible(false);
        window.add(ta_year);

        et_hora = new JLabel();
        et_hora.setVisible(false);
        window.add(et_hora);

        ta_hora = new JTextField();
        ta_hora.setVisible(false);
        window.add(ta_hora);

        et_separadorHora = new JLabel();
        et_separadorHora.setVisible(false);
        window.add(et_separadorHora);

        ta_minutos = new JTextField();
        ta_minutos.setVisible(false);
        window.add(ta_minutos);

        et_doctor = new JLabel();
        et_doctor.setVisible(false);
        window.add(et_doctor);

        spinnerDoctores = new JSpinner(new SpinnerListModel(nombreDoctores()));
        spinnerDoctores.setVisible(false);
        window.add(spinnerDoctores);

        btn_crearcita = new JButton();
        btn_crearcita.setVisible(false);
        window.add(btn_crearcita);
        //Fin UI paciente

        //Inicio UI Doctor
        spinnerRegistroCitas = new JSpinner(new SpinnerListModel((regresaregitrosCitas())));
        spinnerRegistroCitas.setVisible(false);
        window.add(spinnerRegistroCitas);

        et_citas = new JLabel();
        et_citas.setVisible(false);
        window.add(et_citas);

        et_resolcion = new JLabel();
        et_resolcion.setVisible(false);
        window.add(et_resolcion);

        ta_resolucion = new JTextArea();
        ta_resolucion.setVisible(false);
        window.add(ta_resolucion);
        //Fin UI Doctor

        btn_cerrarsesion = new JButton("Cerrar sesion");
        btn_cerrarsesion.setBounds(600,500,150,25);
        btn_cerrarsesion.addActionListener(this);
        btn_cerrarsesion.setVisible(false);
        window.add(btn_cerrarsesion);

        btn_guardarResolucion = new JButton("Guardar");
        btn_guardarResolucion.setBounds(100,500,150,25);
        btn_guardarResolucion.addActionListener(this);
        btn_guardarResolucion.setVisible(false);
        window.add(btn_guardarResolucion);

        btn_borrarDoctor = new JButton("Borrar doctor");
        btn_borrarDoctor.addActionListener(this);
        btn_borrarDoctor.setVisible(false);
        window.add(btn_borrarDoctor);

        btn_cambiarAdmi = new JButton("Cambiar credenciales");
        btn_cambiarAdmi.addActionListener(this);
        btn_cambiarAdmi.setVisible(false);
        window.add(btn_cambiarAdmi);

        ta_doctor = new JTextArea();
        ta_doctor.setVisible(false);
        window.add(ta_doctor);

        et_especialidad = new JLabel();
        et_especialidad.setVisible(false);
        window.add(et_especialidad);

        ta_especialidad = new JTextArea();
        ta_especialidad.setVisible(false);
        window.add(ta_especialidad);

        et_contraDoctor = new JLabel();
        et_contraDoctor.setVisible(false);
        window.add(et_contraDoctor);

        ta_contraDoctor = new JTextArea();
        ta_contraDoctor.setVisible(false);
        window.add(ta_contraDoctor);

        btn_agregarDoctor = new JButton("Agregar doctor");
        btn_agregarDoctor.addActionListener(this);
        btn_agregarDoctor.setVisible(false);
        window.add(btn_agregarDoctor);

        et_paciente = new JLabel();
        et_paciente.setVisible(false);
        window.add(et_paciente);

        spinnerPacientes = new JSpinner(pacienteModel);
        spinnerPacientes.setVisible(false);
        window.add(spinnerPacientes);

        btn_borrarPaciente = new JButton();
        btn_borrarPaciente.addActionListener(this);
        btn_borrarPaciente.setVisible(false);
        window.add(btn_borrarPaciente);

        et_nombre = new JLabel();
        et_nombre.setVisible(false);
        window.add(et_nombre);

        ta_nombre = new JTextArea();
        ta_nombre.setVisible(false);
        window.add(ta_nombre);

        et_contraPaciente = new JLabel();
        et_contraPaciente.setVisible(false);
        window.add(et_contraPaciente);

        ta_contraPaciente = new JTextArea();
        ta_contraPaciente.setVisible(false);
        window.add(ta_contraPaciente);

        btn_agregarPaciente = new JButton();
        btn_agregarPaciente.addActionListener(this);
        btn_agregarPaciente.setVisible(false);
        window.add(btn_agregarPaciente);

        add(window);
        setSize(800,600);
        setVisible(true);


    }

    @Override //Valida los eventos de los botones
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_acceder){ // Se valida credenciales, tipo de usuario y se despliega o ocultan UI.
            //Privilegio nivel 1 es usuario, nivel 2 es doctor, nivel 3 es administrador
            switch (validarPrivilegios()){
                case 1:
                    //TODO: Si no existen doctores en registrados/Disponibles arroja leyenda de no puede crear cita
                    if(nombreDoctores()[0]=="Seleccione un doctor"){
                        JOptionPane.showMessageDialog(null,"No hay doctores disponibles por el momento, validar con administrador");
                    }else {
                        //Se cierra la UI de login
                        ocultarLogin();
                        //Se muestra la UI de cita
                        mostrarPacienteUI();
                    }
                    break;
                case 2:
                    window.remove(spinnerRegistroCitas);
                    //Se cierra la UI de login
                    ocultarLogin();
                    //Se muestra la UI de doctor
                    mostrarDoctorUI();
                    break;
                case 3:
                    //Se cierra la UI de login
                    ocultarLogin();
                    //Se muestra la UI de administrador
                    mostrarAdminUI();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecto");
            }
        }
        else if(e.getSource()==btn_crearcita){ // Se genera una nueva cita y se agrega a la base
            List<Cita> citas= bd.cargarCitas();
            Cita cita;
            if(citas.size()<1){
                cita = new Cita(1,
                        "Fecha: "+ta_dia.getText()+"/"+ta_mes.getText()+"/"+ta_year.getText()+" Hora: "+ta_hora.getText()+":"+ta_minutos.getText(),
                        ta_motivo.getText(),usuario,doctor);
            }
            else {
                cita = new Cita(citas.size()+1,
                        "Fecha: "+ta_dia.getText()+"/"+ta_mes.getText()+"/"+ta_year.getText()+" Hora: "+ta_hora.getText()+":"+ta_minutos.getText(),
                        ta_motivo.getText(),usuario,doctor);
            }
            bd.guardarCita(cita);

            JOptionPane.showMessageDialog(null, "Cita creada correcactamente");
            ta_motivo.setText("");
            ta_dia.setText("");
            ta_mes.setText("");
            ta_year.setText("");
            ta_hora.setText("");
            ta_minutos.setText("");
        }
        else if(e.getSource()==btn_guardarResolucion){ //Se guarda la resolucion de una cita dada por el doctor
            try{
                List<Cita> citas= bd.cargarCitas();
                Cita cita = new Cita(citas.size(),
                        "Fecha: "+ta_dia.getText()+"/"+ta_mes.getText()+"/"+ta_year.getText()+" Hora: "+ta_hora.getText()+":"+ta_minutos.getText(),
                        ta_motivo.getText(),usuario,doctor,ta_resolucion.getText());
                citas.set(((mapaIndexCitas.get(spinnerRegistroCitas.getValue()))-1),cita);
                bd.guardarListaCitas(citas);
                JOptionPane.showMessageDialog(null, "Resolucion guardada correctamente.");
                ta_motivo.setText("");
                ta_resolucion.setText("");
            }catch (Exception exc){
                JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar la resolucion.");
            }

        }
        else if(e.getSource()==btn_cerrarsesion){ //Se cierra UI activas y se activa UI login
            //Se cierra las UI activas
            ocultarPacienteUI();
            ocultarDoctorUI();
            ocultarAdminUI();
            //Se muestra UI login
            mostrarLogin();

        }
        else if(e.getSource()==btn_cambiarAdmi){
            String usuario = ta_usuario.getText();
            String pass = ta_password.getText();
            if(usuario.equals("")||pass.equals("")){
                JOptionPane.showMessageDialog(null, "Complete los campos para poder guardar");
            }else {
                guardarUsuarios(usuario,pass,3);
                ta_usuario.setText("");
                ta_password.setText("");
                JOptionPane.showMessageDialog(null,"Usuario actualizado correctamente, reinicie para aplicar cambios.");
            }
        }
        else if(e.getSource()==btn_borrarDoctor){
            List<Doctor> doctores = bd.cargarDoctores();
            String spiner = spinnerDoctores.getValue().toString();
            if(spinnerDoctores.getValue().toString()=="Seleccione un doctor"){
                JOptionPane.showMessageDialog(null, "No ha seleccionado nigun doctor.");
            }else {
                for(int i=0; i<doctores.size();i++){
                    if(spiner.equals("Doctor(a): "+doctores.get(i).getNombre()+" - "+"Especialidad: "+doctores.get(i).getEspecialidad())){
                        doctores.remove(i);
                        break;
                    }
                }
                bd.guardarDoctores(doctores);
                window.remove(spinnerDoctores);
                spinnerDoctores = new JSpinner(new SpinnerListModel((nombreDoctores())));
                spinnerDoctores.setBounds(250,175,300,25);
                window.add(spinnerDoctores);
                JOptionPane.showMessageDialog(null, "Doctor borrado con exito");
            }
        }
        else if(e.getSource()==btn_agregarDoctor){
            String nombre = ta_doctor.getText();
            String especialidad = ta_especialidad.getText();
            String pass = ta_contraDoctor.getText();
            if(nombre.equals("")||especialidad.equals("")||pass.equals("")){
                JOptionPane.showMessageDialog(null, "Complete los campos para poder guardar");
            }else {
                List<Doctor> doctores = bd.cargarDoctores();
                Doctor doc = new Doctor(nombre,especialidad);
                doctores.add(doc);
                bd.guardarDoctores(doctores);
                guardarUsuarios(nombre,pass,2);
                ta_doctor.setText("");
                ta_especialidad.setText("");
                ta_contraDoctor.setText("");
                window.remove(spinnerDoctores);
                spinnerDoctores = new JSpinner(new SpinnerListModel((nombreDoctores())));
                spinnerDoctores.setBounds(250,175,300,25);
                spinnerDoctores.setVisible(true);
                window.add(spinnerDoctores);
                JOptionPane.showMessageDialog(null, "Doctor guardado con exito");
            }

        }
        else if(e.getSource()==btn_borrarPaciente){
            List<Paciente> pacientes = bd.cargarPacientes();
            String spiner = spinnerPacientes.getValue().toString();
            if(spinnerPacientes.getValue().toString()=="Seleccione un paciente"){
                JOptionPane.showMessageDialog(null, "No ha seleccionado nigun paciente.");
            }else {
                for(int i=0; i<pacientes.size();i++){
                    if(spiner.equals(pacientes.get(i).getNombre())){
                        pacientes.remove(i);
                        break;
                    }
                }
                bd.guardarPacientes(pacientes);
                window.remove(spinnerPacientes);
                spinnerPacientes = new JSpinner(new SpinnerListModel(regresaPacientes()));
                spinnerPacientes.setBounds(575,175,200,25);
                spinnerPacientes.addChangeListener(this);
                window.add(spinnerPacientes);
                JOptionPane.showMessageDialog(null, "Paciente borrado con exito");
            }
        }
        else if(e.getSource()==btn_agregarPaciente){
            String nombre = ta_nombre.getText();
            String pass = ta_contraPaciente.getText();
            if(nombre.equals("")||pass.equals("")){
                JOptionPane.showMessageDialog(null, "Complete los campos para poder guardar");
            }else {
                List<Paciente> pacientes = bd.cargarPacientes();
                Paciente paci = new Paciente(nombre);
                pacientes.add(paci);
                bd.guardarPacientes(pacientes);
                guardarUsuarios(nombre,pass,1);
                ta_nombre.setText("");
                ta_contraPaciente.setText("");
                window.remove(spinnerPacientes);
                spinnerPacientes = new JSpinner(new SpinnerListModel(regresaPacientes()));
                spinnerPacientes.setBounds(575,175,200,25);
                spinnerPacientes.addChangeListener(this);
                window.add(spinnerPacientes);
                JOptionPane.showMessageDialog(null, "Paciente guardado con exito");
            }
        }
    }

    //Arroja el tipo de usuario 1 = paciente, 2 = doctor, 3 = adimistrador.
    public int validarPrivilegios(){
        List<Usuario> users = bd.cargarUsuarios();
        String usuario = ta_usuario.getText();
        String contra = ta_password.getText();
        int privilegio = 0;
        //Si existen usuarios el la base se valida usuario y contraseña
        for(int i=0; i<users.size();i++){
            if(usuario.equals(users.get(i).getUsuario())&&contra.equals(users.get(i).getPassword())){
                privilegio = users.get(i).getPrivilegio();
                this.usuario = usuario;
                break;
            }
        }
        return privilegio;
    }
    //Carga los nombres de los doctores a un arreglo String para agregarlos a un Spinner
    public String[] nombreDoctores(){
        List<Doctor> docs = bd.cargarDoctores();
        if(docs.size()==0){
            String[] doctores = {"Seleccione un doctor"};
            return doctores;
        }else{
            String[] doctores = new String[docs.size()];
            for(int i=0;i<docs.size();i++){
                doctores[i] ="Doctor(a): "+ docs.get(i).getNombre() + " - Especialidad: "+docs.get(i).getEspecialidad();
            }
            return doctores;
        }
    }
    //Carga los registros de las citas creadas a un arreglo String para agregarlos a un Spinner
    public String[] regresaregitrosCitas(){
        List<Cita> listCitas = bd.cargarCitas();
        if(listCitas.size()==0){
            String[] citas = {"No tiene citas para hoy, Exelente trabajo! :)"};
            return citas;
        }else{
            String[] citas= new String[listCitas.size()];
            for(int i=0;i<listCitas.size();i++){
                citas[i] ="Folio: "+ listCitas.get(i).getId() + " | "+listCitas.get(i).getFecha_hora() +" | Paciente: " + listCitas.get(i).getPaciente();
                mapaMotivosCitas.put(("Folio: "+ listCitas.get(i).getId() + " | "+listCitas.get(i).getFecha_hora() +" | Paciente: " + listCitas.get(i).getPaciente()),listCitas.get(i).getMotivo());
                mapaIndexCitas.put(("Folio: "+ listCitas.get(i).getId() + " | "+listCitas.get(i).getFecha_hora() +" | Paciente: " + listCitas.get(i).getPaciente()),listCitas.get(i).getId());
            }
            return citas;
        }
    }
    public String[] regresaUsuarios(){
        List<Usuario> listaUsuarios = bd.cargarUsuarios();
        if(listaUsuarios.size()==0){
            String[] nombreUsuarios = new String[1];
            return nombreUsuarios;
        }else {
            String[] nombreUsuarios = new String[listaUsuarios.size()];
            for(int i=0; i<listaUsuarios.size();i++){
                nombreUsuarios[i]=listaUsuarios.get(i).getUsuario();
            }
            return nombreUsuarios;
        }
    }
    public String[] regresaPacientes(){
        List<Paciente> listaPacientes = bd.cargarPacientes();
        if(listaPacientes.size()==0){
            String[] nombrePacientes = {"Seleccione un paciente"};
            return nombrePacientes;
        }else {
            String[] nombrePacientes = new String[listaPacientes.size()];
            for(int i=0; i<listaPacientes.size();i++){
                nombrePacientes[i]=listaPacientes.get(i).getNombre();
            }
            return nombrePacientes;
        }
    }
    //Despliega la interfaz de login
    public void mostrarLogin(){
        et_titulo.setText("Ingresa tu usuario y contraseña");
        et_titulo.setFont(fuenteTitulo);
        et_titulo.setBounds(100,10,600,50);

        et_usuario.setText("Usuario");
        et_usuario.setBounds(365,100,100,25);
        et_usuario.setFont(fuenteOpcioones);
        et_usuario.setVisible(true);

        ta_usuario.setBounds(300,125,200,25);
        ta_usuario.setText("");
        ta_usuario.setVisible(true);

        et_password.setText("Contraseña");
        et_password.setBounds(350,200,200,25);
        et_password.setFont(fuenteOpcioones);
        et_password.setVisible(true);

        ta_password.setBounds(300,225,200,25);
        ta_password.setText("");
        ta_password.setVisible(true);

        btn_acceder.setText("Acceder");
        btn_acceder.setBounds(350,300,100,25);
        btn_acceder.addActionListener(this);
        btn_acceder.setVisible(true);

        spinnerRegistroCitas.setVisible(false);
    }
    //Oculta la interfaz de login
    public void ocultarLogin(){
        et_usuario.setVisible(false);
        ta_usuario.setVisible(false);
        et_password.setVisible(false);
        ta_password.setVisible(false);
        btn_acceder.setVisible(false);
    }
    //Despliega la interfaz para crear una cita nueva
    public void mostrarPacienteUI(){
        et_titulo.setText("Bienvenido(a): " + usuario);
        et_titulo.setBounds(50,10,500,50);
        et_titulo.setFont(fuenteTitulo);

        et_motivo.setBounds(320,100,180,25);
        et_motivo.setText("Motivo de la cita");
        et_motivo.setFont(fuenteOpcioones);
        et_motivo.setVisible(true);

        ta_motivo.setBounds(100,125,600,100);
        ta_motivo.setVisible(true);
        ta_motivo.setText("");

        et_fecha.setText("Fecha: ");
        et_fecha.setFont(fuenteOpcioones);
        et_fecha.setBounds(150,230,75,25);
        et_fecha.setVisible(true);

        ta_dia.setBounds(225,230,25,25);
        ta_dia.setText("");
        ta_dia.setVisible(true);

        ta_mes.setBounds(260,230,25,25);
        ta_mes.setText("");
        ta_mes.setVisible(true);

        ta_year.setBounds(295,230,25,25);
        ta_year.setText("");
        ta_year.setVisible(true);

        et_separadorfecha1.setText("/");
        et_separadorfecha1.setBounds(250,230,10,25);
        et_separadorfecha1.setFont(fuenteOpcioones);
        et_separadorfecha1.setVisible(true);

        et_separadorfecha2.setText("/");
        et_separadorfecha2.setBounds(285,230,10,25);
        et_separadorfecha2.setFont(fuenteOpcioones);
        et_separadorfecha2.setVisible(true);

        et_separadorHora.setText(":");
        et_separadorHora.setBounds(450,230,10,25);
        et_separadorHora.setFont(fuenteOpcioones);
        et_separadorHora.setVisible(true);

        et_hora.setText("Hora: ");
        et_hora.setBounds(350,230,75,25);
        et_hora.setFont(fuenteOpcioones);
        et_hora.setVisible(true);

        ta_hora.setBounds(425,230,25,25);
        ta_hora.setText("");
        ta_hora.setVisible(true);

        ta_minutos.setText("");
        ta_minutos.setBounds(460,230,25,25);
        ta_minutos.setVisible(true);

        spinnerDoctores.setBounds(200,325,400,25);
        spinnerDoctores.addChangeListener(this);
        spinnerDoctores.setVisible(true);

        et_doctor.setText("Seleccione un doctor: ");
        et_doctor.setBounds(275,300,250,25);
        et_doctor.setFont(fuenteOpcioones);
        et_doctor.setVisible(true);


        btn_crearcita.setText("Crear cita");
        btn_crearcita.setBounds(200,375,400,25);
        btn_crearcita.addActionListener(this);
        btn_crearcita.setVisible(true);

        btn_cerrarsesion.setVisible(true);
    }
    //Oculta la interfaz para crear una cita nueva
    public void ocultarPacienteUI(){
        et_motivo.setVisible(false);
        ta_motivo.setVisible(false);
        et_fecha.setVisible(false);
        ta_dia.setVisible(false);
        ta_mes.setVisible(false);
        ta_year.setVisible(false);
        et_separadorfecha1.setVisible(false);
        et_separadorfecha2.setVisible(false);
        et_separadorHora.setVisible(false);
        et_hora.setVisible(false);
        ta_hora.setVisible(false);
        ta_minutos.setVisible(false);
        spinnerDoctores.setVisible(false);
        et_doctor.setVisible(false);
        btn_crearcita.setVisible(false);
        btn_cerrarsesion.setVisible(false);
    }
    //Despliega la interfaz para el doctor
    public void mostrarDoctorUI(){
        et_titulo.setText("Bienvenido Doctor(a): " + usuario);
        et_titulo.setBounds(50,10,700,50);
        et_titulo.setFont(fuenteTitulo);

        et_citas.setText("Citas agendadas");
        et_citas.setBounds(100,75,200,25);
        et_citas.setFont(fuenteOpcioones);
        et_citas.setVisible(true);

        //TODO: Spinner debe actualizarse con las nuevas citas agregadas - Resuelto
        spinnerRegistroCitas = new JSpinner(new SpinnerListModel((regresaregitrosCitas())));
        spinnerRegistroCitas.setVisible(false);
        window.add(spinnerRegistroCitas);

        spinnerRegistroCitas.setBounds(100,100,500,25);
        spinnerRegistroCitas.addChangeListener(this);
        spinnerRegistroCitas.setVisible(true);

        et_motivo.setText("Motivo de la cita");
        et_motivo.setFont(fuenteOpcioones);
        et_motivo.setBounds(100,125,180,25);
        et_motivo.setVisible(true);

        ta_motivo.setBounds(100,150,600,100);
        //TODO: Agrega el primer motivo de la cita 1 - Resuelto
        ta_motivo.setText(mapaMotivosCitas.get(spinnerRegistroCitas.getValue()));
        ta_motivo.setVisible(true);

        et_resolcion.setText("Resoluion / Receta");
        et_resolcion.setBounds(100,250,180,25);
        et_resolcion.setFont(fuenteOpcioones);
        et_resolcion.setVisible(true);

        ta_resolucion.setBounds(100,275,600,100);
        ta_resolucion.setVisible(true);

        btn_guardarResolucion.setVisible(true);

        btn_cerrarsesion.setVisible(true);

    }
    //Oculta la interfaz de doctor
    public void ocultarDoctorUI(){
        et_citas.setVisible(false);
        spinnerRegistroCitas.setVisible(false);
        et_resolcion.setVisible(false);
        ta_resolucion.setVisible(false);
        btn_guardarResolucion.setVisible(false);
        btn_cerrarsesion.setVisible(false);
    }
    //Despliega la interfaz de administrador
    public void mostrarAdminUI(){
        et_titulo.setText("Administrador: " + usuario);
        et_titulo.setBounds(50,10,500,50);
        et_titulo.setFont(fuenteTitulo);

        et_usuario.setText("Usuario Admin");
        et_usuario.setBounds(25,150,200,25);
        et_usuario.setFont(fuenteOpcioones);
        et_usuario.setVisible(true);

        ta_usuario.setBounds(25,175,200,25);
        ta_usuario.setText("");
        ta_usuario.setVisible(true);

        et_password.setText("Contraseña Admin");
        et_password.setBounds(25,200,200,25);
        et_password.setFont(fuenteOpcioones);
        et_password.setVisible(true);

        ta_password.setBounds(25,225,200,25);
        ta_password.setText("");
        ta_password.setVisible(true);

        btn_cambiarAdmi.setBounds(25,260,200,25);
        btn_cambiarAdmi.setVisible(true);

        et_motivo.setText("Doctores");
        et_motivo.setBounds(250,150, 200,25);
        et_motivo.setFont(fuenteOpcioones);
        et_motivo.setVisible(true);

        spinnerDoctores.setBounds(250,175,300,25);
        spinnerDoctores.setVisible(true);

        btn_borrarDoctor.setBounds(325,210,150,25);
        btn_borrarDoctor.setVisible(true);

        et_doctor.setText("Nombre doctor");
        et_doctor.setBounds(250,250,300,25);
        et_doctor.setFont(fuenteOpcioones);
        et_doctor.setVisible(true);

        ta_doctor.setBounds(250,275,300,25);
        ta_doctor.setText("");
        ta_doctor.setVisible(true);

        et_especialidad.setText("Especialidad");
        et_especialidad.setBounds(250,300,300,25);
        et_especialidad.setFont(fuenteOpcioones);
        et_especialidad.setVisible(true);

        ta_especialidad.setBounds(250,325,300,25);
        ta_especialidad.setText("");
        ta_especialidad.setVisible(true);

        et_contraDoctor.setBounds(250,350,300,25);
        et_contraDoctor.setText("Contraseña");
        et_contraDoctor.setFont(fuenteOpcioones);
        et_contraDoctor.setVisible(true);

        ta_contraDoctor.setBounds(250,375,300,25);
        ta_contraDoctor.setText("");
        ta_contraDoctor.setVisible(true);

        btn_agregarDoctor.setBounds(325,440,150,25);
        btn_agregarDoctor.setVisible(true);

        et_paciente.setText("Pacientes");
        et_paciente.setBounds(575,150,200,25);
        et_paciente.setFont(fuenteOpcioones);
        et_paciente.setVisible(true);

        spinnerPacientes.setBounds(575,175,200,25);
        spinnerPacientes.addChangeListener(this);
        spinnerPacientes.setVisible(true);

        btn_borrarPaciente.setText("Borrar paciente");
        btn_borrarPaciente.setBounds(600,210,150,25);
        btn_borrarPaciente.setVisible(true);

        et_nombre.setText("Nombre del paciente");
        et_nombre.setBounds(575,250,200,25);
        et_nombre.setFont(fuenteOpcioones);
        et_nombre.setVisible(true);

        ta_nombre.setBounds(575,275,200,25);
        ta_nombre.setText("");
        ta_nombre.setVisible(true);

        et_contraPaciente.setBounds(575,300,200,25);
        et_contraPaciente.setText("Contraseña");
        et_contraPaciente.setFont(fuenteOpcioones);
        et_contraPaciente.setVisible(true);

        ta_contraPaciente.setBounds(575,325,200,25);
        ta_contraPaciente.setText("");
        ta_contraPaciente.setVisible(true);

        btn_agregarPaciente.setText("Agregar paciente");
        btn_agregarPaciente.setBounds(600,440,150,25);
        btn_agregarPaciente.setVisible(true);

        btn_cerrarsesion.setVisible(true);
    }
    //Oculta la interfaz de administrador
    public void ocultarAdminUI(){
        et_usuario.setVisible(false);
        ta_usuario.setVisible(false);
        et_password.setVisible(false);
        ta_password.setVisible(false);
        btn_cambiarAdmi.setVisible(false);
        et_motivo.setVisible(false);
        spinnerDoctores.setVisible(false);
        btn_borrarDoctor.setVisible(false);
        et_doctor.setVisible(false);
        ta_doctor.setVisible(false);
        et_especialidad.setVisible(false);
        ta_especialidad.setVisible(false);
        et_contraDoctor.setVisible(false);
        ta_contraDoctor.setVisible(false);
        btn_agregarDoctor.setVisible(false);
        et_paciente.setVisible(false);
        spinnerPacientes.setVisible(false);
        btn_borrarPaciente.setVisible(false);
        et_nombre.setVisible(false);
        ta_nombre.setVisible(false);
        et_contraPaciente.setVisible(false);
        ta_contraPaciente.setVisible(false);
        btn_agregarPaciente.setVisible(false);
        btn_cerrarsesion.setVisible(false);
    }
    public void guardarUsuarios(String usurio, String pass, int privilegio){
        List<Usuario> Ususrios = bd.cargarUsuarios();
        Usuario user = new Usuario(usurio,pass,privilegio);
        Ususrios.add(user);
        bd.actualizaUsuarios(Ususrios);
    }

    @Override //Valida los cambios de los spinners
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()==spinnerDoctores){
            doctor = spinnerDoctores.getValue().toString();
        }
        else if(e.getSource()==spinnerRegistroCitas)
            ta_motivo.setText(mapaMotivosCitas.get(spinnerRegistroCitas.getValue()));

    }
}
