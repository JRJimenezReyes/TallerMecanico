package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.FuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariadb.FuenteDatosMariaDb;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mongodb.FuenteDatosMongoDb;


public enum FabricaFuenteDatos {
    FICHEROS {
        @Override
        public IFuenteDatos crear() {
            return new FuenteDatos();
        }
    },
    MONGODB {
        @Override
        public IFuenteDatos crear(){
            return new FuenteDatosMongoDb();
        }
    },
    MARIADB {
        @Override
        public IFuenteDatos crear() {
            return new FuenteDatosMariaDb();
        }
    };





    public abstract IFuenteDatos crear();

}