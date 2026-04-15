package com.utez.kanban.kanban.domain.model;

public enum StatusKanban {
    ToDo,
    Doing,
    Done;


    public static String normalize(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("El status no puede ser nulo o vacío");
        }

        // Quitamos espacios en blanco extra y pasamos todo a mayúsculas
        String cleanStatus = status.trim().toUpperCase();

        switch (cleanStatus) {
            case "TODO":
                return ToDo.name();  // "ToDo"
            case "DOING":
                return Doing.name(); // "Doing"
            case "DONE":
                return Done.name();  // "Done"
            default:
                throw new IllegalArgumentException("Estatus inválido: " + status);
        }
    }
}
