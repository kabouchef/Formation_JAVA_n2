package com.company.jdbc.serveurs;

public enum ServerLMFR {
    //Objets directement construits
    PROD_REEL("PROD_REEL", "jdbc:oracle:thin:@PFRLMDBOAAD01:1521:poaadb", "oaa", "oaa" ),
    PREPROD_REEL ("PREPROD_REEL", "jdbc:oracle:thin:@afrlmdboaad01:1521:aoaadb", "oaa", "oaa" ),
    RECETTE_REEL ("RECETTE_REEL", "jdbc:oracle:thin:@rfrlmdboaag01:1521:roaag01", "oaa", "oaa" ),
    DEV_REEL ("DEV_REEL", "jdbc:oracle:thin:@dfrlmdboaad01:1521:doaad01", "oaa", "oaa" ),

    PROD_SIMUL("PROD_SIMUL", "jdbc:oracle:thin:@PFRLMDBOAAD01:1521:poaadb", "oaasim", "oaasim" ),
    PREPROD_SIMUL ("PREPROD_SIMUL", "jdbc:oracle:thin:@afrlmdboaad01:1521:aoaadb", "oaasim", "oaasim" ),
    RECETTE_SIMUL ("RECETTE_SIMUL", "jdbc:oracle:thin:@rfrlmdboaag01:1521:roaag01", "oaasim", "oaasim" ),
    DEV_SIMUL ("DEV_SIMUL", "jdbc:oracle:thin:@dfrlmdboaad01:1521:doaad01", "oaasim", "oaasim" );

    private String name = "";
    private String url = "";
    private String user = "";
    private String password = "";

    ServerLMFR(String name, String url, String user, String password) {
        this.name = name;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String toString(){
        return name;
    }


}
