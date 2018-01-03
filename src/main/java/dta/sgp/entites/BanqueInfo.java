package dta.sgp.entites;

import javax.persistence.Embeddable;

@Embeddable
public class BanqueInfo {
	
	private String banque;
	private String bic;
	private String iban;
	
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
}
