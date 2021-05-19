package proyecto.model;

import java.util.HashMap;

public class Subject {
    private String idSub;
    private String nameSubj;
    private String desc;
    private String status;
    private HashMap<String,Group> groups;
    
    public Subject(String idSub, String nameSubj, String desc, String stat) {
        this.idSub = idSub;
        this.nameSubj = nameSubj;
        this.desc = desc;
        this.status = stat; // "OFERTA" || "INACTIVO"
        this.groups = new HashMap<>();
    }


    public Subject() {
        this("", "", "", "");
    }

    public void insertGroups(Group gr) {
        gr.setSubName(nameSubj);
        if(groups.get(gr.getNrc()) == null)
            groups.put(gr.getNrc(),gr);
    }

    public void deleteGroups(String nrc) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getNrc().equals(nrc)) {
                groups.remove(i);
            }
        }
    }

    public String showGroup(int pos) {
        return groups.get(pos).show();
    }
    
    public String show(){
        String f = "", tn = idSub, tc = nameSubj, numS = Integer.toString(groups.size());
        f = f + "Identificador de curso: " + tn
                + "\n" + "Nombre del curso: " + tc
                + "\n" + "Grupos disponibles: " + numS
                + "\n";
        return f;
    }

    public HashMap<String, Group> getGroups() {
        return groups;
    }

    public void setGroups(HashMap<String, Group> groups) {
        this.groups = groups;
    }

    public String getIdSub() {
        return idSub;
    }

    public void setIdSub(String ID) {
        this.idSub = ID;
    }
    public String getNameSubj() {
        return nameSubj;
    }

    public void setNameSubj(String nameSubj) {
        this.nameSubj = nameSubj;
    }

    public String getDesc() {
        return desc;
    }

    public void setDes(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    
    
}


