package es.rul3s.raul.wifisecurityauditor;

/**
 * Created by Raul on 28/03/2016.
 */
public class WifiDetails {
    private String bssid;
    private String essid;
    private String security;
    private String brand;
    private String model;
    private int channel;

    public WifiDetails(){
    }

    public WifiDetails(String bssid, String essid, String security, int channel){
        this.bssid = bssid;
        this.essid = essid;
        this.security = security;
        this.channel = channel;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getEssid() {
        return essid;
    }

    public void setEssid(String essid) {
        this.essid = essid;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}
