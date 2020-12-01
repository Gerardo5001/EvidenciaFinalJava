package DataBase;

import sun.security.krb5.internal.PAData;

import javax.print.Doc;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ControlBD {


        private final File fileDoctores = new File("C:/ConsultorioBD/doctores.csv");
        private final File filePacientes = new File("C:/ConsultorioBD/pacientes.csv");
        private final File fileUsuarios = new File("C:/ConsultorioBD/usuarios.csv");
        private final File fileRegistroCitas = new File("C:/ConsultorioBD/registroCitas.csv");
        private BufferedReader entrada;

        //Valida el directorio si no existe lo crea
        public void validaDirectorio() throws IOException {
            FileWriter writer;
            File directorio = new File("C:/ConsultorioBD");
            //Se valida directorio, si no existe se crea y se crean los archivos
            if (!directorio.exists()) {
                System.out.println("Directorio creado");
                directorio.mkdirs();
                try
                {
                    writer = new FileWriter(fileDoctores);
                    writer = new FileWriter(filePacientes);
                    writer = new FileWriter(fileUsuarios);
                    writer = new FileWriter(fileRegistroCitas);

                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Error" + e);
                }
            }
            // Se validan los archivos, si no existen se crean
            else {
                validaArchivos(fileDoctores);
                validaArchivos(filePacientes);
                validaArchivos(fileUsuarios);
                validaArchivos(fileRegistroCitas);
            }

        }
        // Valida si existen los archivos de no ser asi los crea
        public void validaArchivos(File archivo){
            if(!archivo.exists()){
                FileWriter writer;
                try
                {
                    writer = new FileWriter(archivo);
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Error" + e);
                }
            }
        }
        // Valida usuarios, si no existe un adimistrador pide crearlo
        public List<Usuario> cargarUsuarios(){
            Usuario user1 = new Usuario(1,"admin","admin",3);
            ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
            listUsuarios.add(user1);
            try {
                entrada = new BufferedReader(new FileReader(fileUsuarios));
                String linea;
                    while ((linea = entrada.readLine()) != null) {
                        String[] acomodo = linea.split(",");
                        Usuario user = new Usuario(Integer.parseInt(acomodo[0]),acomodo[1],acomodo[2],Integer.parseInt(acomodo[3]));
                        listUsuarios.add(user);
                    }
                    entrada.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "File Error: " + e.toString());
            }
            return listUsuarios;
        }
        //Carga los doctores del archivo en una lista
        public List<Doctor> cargarDoctores(){
            ArrayList<Doctor> listDoctores = new ArrayList<>();
            try {
                entrada = new BufferedReader(new FileReader(fileDoctores));
                String linea;
                while ((linea = entrada.readLine())!=null){
                    String[] acomodo = linea.split(",");
                    Doctor doc = new Doctor(Integer.parseInt(acomodo[0]),acomodo[1],acomodo[2]);
                    listDoctores.add(doc);
                }
                entrada.close();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            }
            return listDoctores;
        }
        //Carga las citas en una lista
        public List<Cita> cargarCitas(){
            ArrayList<Cita> listaCitas = new ArrayList<>();
            try {
                entrada = new BufferedReader(new FileReader(fileRegistroCitas));
                String linea;
                while ((linea = entrada.readLine())!=null){
                    String[] acomodo = linea.split(",");
                    Cita cit = new Cita(Integer.parseInt(acomodo[0]),acomodo[1],acomodo[2],acomodo[3],acomodo[4]);
                    listaCitas.add(cit);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            }
            return listaCitas;
        }
        //Guarda una nueva lista
        public void guardarCita(Cita cita){
            FileWriter writer;
            BufferedWriter bufferedReader;
            PrintWriter printWriter;
            List<Cita> citas = cargarCitas();
            citas.add(cita);
                    try {
                        writer = new FileWriter(fileRegistroCitas);
                        bufferedReader = new BufferedWriter(writer);
                        printWriter =new PrintWriter(bufferedReader);
                        for(int i=0; i<citas.size();i++){
                            printWriter.write(citas.get(i).getId()+","+citas.get(i).getFecha_hora()+","+citas.get(i).getMotivo()+
                                    ","+citas.get(i).getPaciente()+","+citas.get(i).getDoctor()+"\n");
                        }
                        printWriter.close();
                        bufferedReader.close();
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Error: " + e.toString());
                    }

        }
        //Remplaza y guarda una cita resuelta
        public void guardarListaCitas(List<Cita> citas){
            FileWriter writer;
            BufferedWriter bufferedReader;
            PrintWriter printWriter;
            try {
                writer = new FileWriter(fileRegistroCitas);
                bufferedReader = new BufferedWriter(writer);
                printWriter =new PrintWriter(bufferedReader);
                for(int i=0; i<citas.size();i++){
                    printWriter.write(citas.get(i).getId()+","+citas.get(i).getFecha_hora()+","+citas.get(i).getMotivo()+
                            ","+citas.get(i).getPaciente()+","+citas.get(i).getDoctor()+","+citas.get(i).getResolucion()+"\n");
                }
                printWriter.close();
                bufferedReader.close();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            }
        }
        public void actualizaUsuarios(List<Usuario> usuarios){
                    FileWriter writer;
                    BufferedWriter bufferedReader;
                    PrintWriter printWriter;
                    try {
                        writer = new FileWriter(fileUsuarios);
                        bufferedReader = new BufferedWriter(writer);
                        printWriter =new PrintWriter(bufferedReader);
                        for(int i=0; i<usuarios.size();i++){
                            printWriter.write((i+1)+","+usuarios.get(i).getUsuario()+","+usuarios.get(i).getPassword()+","+usuarios.get(i).getPrivilegio()+"\n");
                        }
                        printWriter.close();
                        bufferedReader.close();
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null, "Error al guardar cambio usuarios: " + e.toString());
                    }

        }
        public void guardarDoctores(List<Doctor> doctores){
            FileWriter writer;
            BufferedWriter bufferedReader;
            PrintWriter printWriter;
            try {
                writer = new FileWriter(fileDoctores);
                bufferedReader = new BufferedWriter(writer);
                printWriter =new PrintWriter(bufferedReader);
                for(int i=0; i<doctores.size();i++){
                    printWriter.write((i+1)+","+doctores.get(i).getNombre()+","+doctores.get(i).getEspecialidad()+"\n");
                }
                printWriter.close();
                bufferedReader.close();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error al guardar cambio doctores: " + e.toString());
            }
        }
        public List<Paciente> cargarPacientes(){
            ArrayList<Paciente> listaPacientes = new ArrayList<>();
            try {
                entrada = new BufferedReader(new FileReader(filePacientes));
                String linea;
                while ((linea = entrada.readLine())!=null){
                    String[] acomodo = linea.split(",");
                    Paciente paciente = new Paciente(Integer.parseInt(acomodo[0]),acomodo[1]);
                    listaPacientes.add(paciente);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            }
            return listaPacientes;
        }
        public void guardarPacientes(List<Paciente> pacientes){
            FileWriter writer;
            BufferedWriter bufferedReader;
            PrintWriter printWriter;
            try {
                writer = new FileWriter(filePacientes);
                bufferedReader = new BufferedWriter(writer);
                printWriter =new PrintWriter(bufferedReader);
                for(int i=0; i<pacientes.size();i++){
                    printWriter.write((i+1)+","+pacientes.get(i).getNombre()+"\n");
                }
                printWriter.close();
                bufferedReader.close();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error al guardar cambio pacientes: " + e.toString());
            }
        }

}
