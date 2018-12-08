package com.company;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public abstract class AbstractStaticSingleton {

    public abstract JTable GetTable();

    public abstract File LoadPath();

    public abstract void ReadAllFile(JPanel panel)  throws IOException;

    public abstract float CalculTotalImc(float weight, float cut);

    public abstract float GetTotalWeight(JTable table);

    public abstract float GetTotalCut(JTable table);
}
