package com.company;

public class SingletonFactory {

    public static String className = SingletonFactory.class.getName();

    public enum FactorySingletonType {

        Xml, Csv, Json
    }

    /**
     * @param type
     * @return AbstractStatisticSingleton
     */
    public static AbstractStaticSingleton GetFactory(String type)
    {
        if (type.equals("xml"))
        {
            return new Xml();
        }
        else if (type.equals("csv"))
        {
            return new Csv();
        }
        else if (type.equals("json"))
        {
            return new Json();
        }

        return null;
    }
}
