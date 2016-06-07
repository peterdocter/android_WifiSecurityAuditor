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

    public int getChannelNumber(){
        int channelNum;
        switch (channel){
            case 2412:
                channelNum = 1;
                break;
            case 2417:
                channelNum = 2;
                break;
            case 2422:
                channelNum = 3;
                break;
            case 2427:
                channelNum = 4;
                break;
            case 2432:
                channelNum = 5;
                break;
            case 2437:
                channelNum = 6;
                break;
            case 2442:
                channelNum = 7;
                break;
            case 2447:
                channelNum = 8;
                break;
            case 2452:
                channelNum = 9;
                break;
            case 2457:
                channelNum = 10;
                break;
            case 2462:
                channelNum = 11;
                break;
            case 2467:
                channelNum = 12;
                break;
            case 2472:
                channelNum = 13;
                break;
            case 2477:
                channelNum = 14;
                break;
            case 2482:
                channelNum = 15;
                break;
            default: channelNum = -1;
                break;
        }
        return channelNum;
    }
}
