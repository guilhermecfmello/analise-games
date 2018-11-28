package model;

import java.util.ArrayList;

public class Diagnosis {
	private ArrayList<DMF> DMFSteamList;
	private ArrayList<DMF> DMFNuuvemList;
	private ArrayList<DMF> DMFGamersGateList;
	private ArrayList<CMF> CMFSteamList;
	private ArrayList<CMF> CMFNuuvemList;
	private ArrayList<CMF> CMFGamersGateList;
	
	
	
	public String getDMFNuuvemDataString() {
		String s = "";
		DMF item;
		item = this.DMFNuuvemList.get(0);
	    s += "["+item.getCount()+"";
	    for(int i = 1 ; i < this.DMFNuuvemList.size(); i++){
	  	 item = this.DMFNuuvemList.get(i);
	  	 s += ","+ item.getCount();
	    }
	    s += "]";
		return s;	
	}
	
	public String getDMFNuuvemLabelString() {
		String s = "";
		DMF item;
		item = this.DMFNuuvemList.get(0);
	    s += "['"+item.getName_dev()+"'";
	    for(int i = 1 ; i < this.DMFNuuvemList.size(); i++){
	  	 item = this.DMFNuuvemList.get(i);
	  	 s += ",'"+ item.getName_dev()+"'";
	    }
	    s += "]";
		return s;
	}
	
	
	
	public String getDMFSteamLabelString() {
		String s = "";
		DMF item;
		item = this.DMFSteamList.get(0);
	    s += "['"+item.getName_dev()+"'";
	    for(int i = 1 ; i < this.DMFSteamList.size(); i++){
	  	 item = this.DMFSteamList.get(i);
	  	 s += ",'"+ item.getName_dev()+"'";
	    }
	    s += "]";
		return s;
	}
	
	public String getDMFSteamDataString() {
		String s = "";
		DMF item;
		item = this.DMFSteamList.get(0);
	    s += "["+item.getCount()+"";
	    for(int i = 1 ; i < this.DMFSteamList.size(); i++){
	  	 item = this.DMFSteamList.get(i);
	  	 s += ","+ item.getCount();
	    }
	    s += "]";
		return s;	
	}
	
	public ArrayList<CMF> getCMFSteamList() {
		return CMFSteamList;
	}

	public void setCMFSteamList(ArrayList<CMF> cMFSteamList) {
		CMFSteamList = cMFSteamList;
	}

	public ArrayList<CMF> getCMFNuuvemList() {
		return CMFNuuvemList;
	}

	public void setCMFNuuvemList(ArrayList<CMF> cMFNuuvemList) {
		CMFNuuvemList = cMFNuuvemList;
	}

	public ArrayList<CMF> getCMFGamersGateList() {
		return CMFGamersGateList;
	}

	public void setCMFGamersGateList(ArrayList<CMF> cMFGamersGateList) {
		CMFGamersGateList = cMFGamersGateList;
	}

	public ArrayList<DMF> getDMFSteamList() {
		return DMFSteamList;
	}

	public ArrayList<DMF> getDMFNuuvemList() {
		return DMFNuuvemList;
	}

	public void setDMFNuuvemList(ArrayList<DMF> dMFNuuvemList) {
		DMFNuuvemList = dMFNuuvemList;
	}

	public ArrayList<DMF> getDMFGamersGateList() {
		return DMFGamersGateList;
	}

	public void setDMFGamersGateList(ArrayList<DMF> dMFGamersGateList) {
		DMFGamersGateList = dMFGamersGateList;
	}

	public void setDMFSteamList(ArrayList<DMF> dMFSteamList) {
		DMFSteamList = dMFSteamList;
	}
	
	
}
