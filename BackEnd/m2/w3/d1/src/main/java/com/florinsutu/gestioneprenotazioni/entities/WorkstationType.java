package com.florinsutu.gestioneprenotazioni.entities;

public enum WorkstationType {
PRIVATE,
OPENSPACE,
MEETING_ROOM;

@Override
public String toString() {
	return this.name();
}
}

