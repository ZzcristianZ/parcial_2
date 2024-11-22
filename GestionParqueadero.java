public class GestionParqueadero {

    public static class Vehiculo {
        private String placa;
        private String tipo;
        private String propietario;

        public Vehiculo(String placa, String tipo, String propietario) {
            this.placa = placa;
            this.tipo = tipo;
            this.propietario = propietario;
        }

        public String getPlaca() {
            return placa;
        }

        public String getTipo() {
            return tipo;
        }

        public String getPropietario() {
            return propietario;
        }
    }



    public static class EspacioParqueadero {
        private int numeroEspacio;
        private String tipoPermitido;
        private boolean ocupado;

        public EspacioParqueadero(int numeroEspacio, String tipoPermitido) {
            this.numeroEspacio = numeroEspacio;
            this.tipoPermitido = tipoPermitido;
            this.ocupado = false;
        }

        public boolean asignarVehiculo(Vehiculo vehiculo) {
            if (!ocupado && vehiculo.getTipo().equals(tipoPermitido)) {
                ocupado = true;
                System.out.println("Espacio " + numeroEspacio + " asignado a " + vehiculo.getPlaca());
                return true;
            }
            return false;
        }

        public void liberarEspacio() {
            ocupado = false;
            System.out.println("Espacio " + numeroEspacio + " liberado.");
        }

        public int getNumeroEspacio() {
            return numeroEspacio;
        }

        public String getTipoPermitido() {
            return tipoPermitido;
        }

        public boolean estaOcupado() {
            return ocupado;
        }
    }

    private Vehiculo[] vehiculos;
    private EspacioParqueadero[] espacios;
    private int indiceVehiculos;
    private int indiceEspacios;

    public GestionParqueadero(int maxVehiculos, int maxEspacios) {
        vehiculos = new Vehiculo[maxVehiculos];
        espacios = new EspacioParqueadero[maxEspacios];
        indiceVehiculos = 0;
        indiceEspacios = 0;
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        if (indiceVehiculos < vehiculos.length) {
            vehiculos[indiceVehiculos++] = vehiculo;
            System.out.println("Vehículo " + vehiculo.getPlaca() + " registrado.");
        } else {
            System.out.println("No se pueden registrar más vehículos.");
        }
    }

    public void registrarEspacio(EspacioParqueadero espacio) {
        if (indiceEspacios < espacios.length) {
            espacios[indiceEspacios++] = espacio;
            System.out.println("Espacio " + espacio.getNumeroEspacio() + " registrado.");
        } else {
            System.out.println("No se pueden registrar más espacios.");
        }
    }

    public void mostrarEspaciosDisponibles() {
        for (int i = 0; i < indiceEspacios; i++) {
            if (!espacios[i].estaOcupado()) {
                System.out.println("Espacio " + espacios[i].getNumeroEspacio() + " para " + espacios[i].getTipoPermitido() + " está disponible.");
            }
        }
    }

    public void asignarEspacio(Vehiculo vehiculo) {
        for (int i = 0; i < indiceEspacios; i++) {
            if (!espacios[i].estaOcupado() && espacios[i].getTipoPermitido().equals(vehiculo.getTipo())) {
                if (espacios[i].asignarVehiculo(vehiculo)) {
                    return;
                }
            }
        }
        System.out.println("No hay espacios disponibles para " + vehiculo.getTipo() + ".");
    }

    public static void main(String[] args) {
        GestionParqueadero gestion = new GestionParqueadero(10, 10);

        Vehiculo carro1 = new Vehiculo("ABC123", "carro", "Juan Perez");
        Vehiculo moto1 = new Vehiculo("XYZ987", "moto", "Maria Gomez");

        EspacioParqueadero espacio1 = new EspacioParqueadero(1, "carro");
        EspacioParqueadero espacio2 = new EspacioParqueadero(2, "moto");

        gestion.registrarVehiculo(carro1);
        gestion.registrarVehiculo(moto1);

        gestion.registrarEspacio(espacio1);
        gestion.registrarEspacio(espacio2);

        gestion.mostrarEspaciosDisponibles();

        gestion.asignarEspacio(carro1);
        gestion.asignarEspacio(moto1);

        gestion.mostrarEspaciosDisponibles();

        espacio1.liberarEspacio();
        gestion.mostrarEspaciosDisponibles();
    }
}
    
    
