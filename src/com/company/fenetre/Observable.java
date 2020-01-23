package com.company.fenetre;

public interface Observable {
    void addObservateur(Observateur obs);
    void updateObservateur();
    void delObservateur();
}
