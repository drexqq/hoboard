package member;


import java.io.Serializable;
import java.util.Arrays;

public class BUSI_Cate_Dto extends Member_Dto implements Serializable {

	
	private String id;
	private String internal;
	private String anpn;
	private String mtrnt;
	private String pdtrc;
	private String nrlgy;
	private String nrsrg;
	private String crdlg;
	private String xray;
	private String gs;
	private String dprtm;
	private String os;
	private String rhblt;
	private String thrcc;
	private String skin_uro;
	private String dent;
	private String ophth;
	
	public BUSI_Cate_Dto() {}

	public BUSI_Cate_Dto(String internal, String anpn, String mtrnt, String pdtrc, String nrlgy, String nrsrg,
			String crdlg, String xray, String gs, String dprtm, String os, String rhblt, String thrcc, String skin_uro,
			String dent, String ophth) {
		super();
		this.internal = internal;
		this.anpn = anpn;
		this.mtrnt = mtrnt;
		this.pdtrc = pdtrc;
		this.nrlgy = nrlgy;
		this.nrsrg = nrsrg;
		this.crdlg = crdlg;
		this.xray = xray;
		this.gs = gs;
		this.dprtm = dprtm;
		this.os = os;
		this.rhblt = rhblt;
		this.thrcc = thrcc;
		this.skin_uro = skin_uro;
		this.dent = dent;
		this.ophth = ophth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInternal() {
		return internal;
	}

	public void setInternal(String internal) {
		this.internal = internal;
	}

	public String getAnpn() {
		return anpn;
	}

	public void setAnpn(String anpn) {
		this.anpn = anpn;
	}

	public String getMtrnt() {
		return mtrnt;
	}

	public void setMtrnt(String mtrnt) {
		this.mtrnt = mtrnt;
	}

	public String getPdtrc() {
		return pdtrc;
	}

	public void setPdtrc(String pdtrc) {
		this.pdtrc = pdtrc;
	}

	public String getNrlgy() {
		return nrlgy;
	}

	public void setNrlgy(String nrlgy) {
		this.nrlgy = nrlgy;
	}

	public String getNrsrg() {
		return nrsrg;
	}

	public void setNrsrg(String nrsrg) {
		this.nrsrg = nrsrg;
	}

	public String getCrdlg() {
		return crdlg;
	}

	public void setCrdlg(String crdlg) {
		this.crdlg = crdlg;
	}

	public String getXray() {
		return xray;
	}

	public void setXray(String xray) {
		this.xray = xray;
	}

	public String getGs() {
		return gs;
	}

	public void setGs(String gs) {
		this.gs = gs;
	}

	public String getDprtm() {
		return dprtm;
	}

	public void setDprtm(String dprtm) {
		this.dprtm = dprtm;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getRhblt() {
		return rhblt;
	}

	public void setRhblt(String rhblt) {
		this.rhblt = rhblt;
	}

	public String getThrcc() {
		return thrcc;
	}

	public void setThrcc(String thrcc) {
		this.thrcc = thrcc;
	}

	public String getSkin_uro() {
		return skin_uro;
	}

	public void setSkin_uro(String skin_uro) {
		this.skin_uro = skin_uro;
	}

	public String getDent() {
		return dent;
	}

	public void setDent(String dent) {
		this.dent = dent;
	}

	public String getOphth() {
		return ophth;
	}

	public void setOphth(String ophth) {
		this.ophth = ophth;
	}

	@Override
	public String toString() {
		return "BUSI_Cate_Dto [id=" + id + ", internal=" + internal + ", anpn=" + anpn + ", mtrnt=" + mtrnt + ", pdtrc="
				+ pdtrc + ", nrlgy=" + nrlgy + ", nrsrg=" + nrsrg + ", crdlg=" + crdlg + ", xray=" + xray + ", gs=" + gs
				+ ", dprtm=" + dprtm + ", os=" + os + ", rhblt=" + rhblt + ", thrcc=" + thrcc + ", skin_uro=" + skin_uro
				+ ", dent=" + dent + ", ophth=" + ophth + "]";
	}
}