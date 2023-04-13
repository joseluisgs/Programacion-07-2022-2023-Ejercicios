package exception.ingredienteException;

public sealed class IngredienteException extends RuntimeException{
    public IngredienteException(String mes) {
        super(mes);
    }
    public static final class IngredienteNotFoundException extends exception.ingredienteException.IngredienteException {
        public IngredienteNotFoundException(String mes) {
            super("Error al buscar ingrediente, el ingrediente de id: "+mes+", no se ha encontrado.");
        }
    }
    public static final class IngredienteBadRequestException extends exception.ingredienteException.IngredienteException {
        public IngredienteBadRequestException(String mes) {
            super("Error al introducir datos del ingrediente, el dato "+mes+", no es valido.");
        }
    }
    public static final class IngredienteAlreadyExistsException extends exception.ingredienteException.IngredienteException {
        public IngredienteAlreadyExistsException(String mes) {
            super("Error al añadir ingrediente, el ingrediente de id: "+mes+", ya existe.");
        }
    }
}

