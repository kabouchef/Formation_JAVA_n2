package com.company.personnage;

import com.company.comportement.*;

public class Medecin extends Personnage{
    public Medecin() {
        this.soin = new PremierSoin();
    }
    public Medecin(EspritCombatif esprit, Soin soin, Deplacement dep) {
        super(esprit, soin, dep);
    }
}