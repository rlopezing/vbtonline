package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class SummaryTransfersToOtherBanksOd implements Serializable, Cloneable {
    private String account;
    private String accountCode;
    private String beneficiaryName;
    private String beneficiaryAccountBank;
    private String beneficiaryAccount;
    private String beneficiaryEmail;
    private String beneficiaryAddress1;
    private String beneficiaryAddress2;
    private String beneficiaryAddress3;
    private String beneficiaryTelephone;
    private String beneficiaryCountry;
    private String beneficiaryCountryCode;
    private String beneficiaryBankCodeType;
    private String beneficiaryBankCodeNumber;
    private String beneficiaryBankCodeTypeSwift;
    private String beneficiaryBankCodeNumberSwift;
    private String beneficiaryBankName;
    private String beneficiaryBankAddress1;
    private String beneficiaryBankAddress2;
    private String beneficiaryBankAddress3;
    private String beneficiaryBankCountry;
    private String beneficiaryBankCountryCode;
    private String intermediaryBankCodeType;
    private String intermediaryBankCodeNumber;
    private String intermediaryBankCodeTypeSwift;
    private String intermediaryBankCodeNumberSwift;
    private String intermediaryBankName;
    private String intermediaryBankAddress1;
    private String intermediaryBankAddress2;
    private String intermediaryBankAddress3;
    private String intermediaryBankCountry;
    private String intermediaryBankCountryCode;
    private String furtherCreditAccount;
    private String furtherCreditName;
    private String amount;
    private String recieverName;
    private String moneda;
    private String nombreTemplate;
    private String claveTemporal;
    private String monto_aux;
    private String motivo;
    private String statusAprobacion;


    public Object clone() {
        Object obj = null;
        try {
            obj = (SummaryTransfersToOtherBanksOd)super.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return obj;
    }


    public String getStatusAprobacion() {
        return statusAprobacion;
    }

    public void setStatusAprobacion(String statusAprobacion) {
        this.statusAprobacion = statusAprobacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMonto_aux() {
        return monto_aux;
    }

    public void setMonto_aux(String monto_aux) {
        this.monto_aux = monto_aux;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public String getBeneficiaryAccountBank() {
        return beneficiaryAccountBank;
    }

    public void setBeneficiaryAccountBank(String beneficiaryAccountBank) {
        this.beneficiaryAccountBank = beneficiaryAccountBank;
    }

    public String getBeneficiaryEmail() {
        return beneficiaryEmail;
    }

    public void setBeneficiaryEmail(String beneficiaryEmail) {
        this.beneficiaryEmail = beneficiaryEmail;
    }

    public String getBeneficiaryAddress1() {
        return beneficiaryAddress1;
    }

    public void setBeneficiaryAddress1(String beneficiaryAddress1) {
        this.beneficiaryAddress1 = beneficiaryAddress1;
    }

    public String getBeneficiaryAddress2() {
        return beneficiaryAddress2;
    }

    public void setBeneficiaryAddress2(String beneficiaryAddress2) {
        this.beneficiaryAddress2 = beneficiaryAddress2;
    }

    public String getBeneficiaryAddress3() {
        return beneficiaryAddress3;
    }

    public void setBeneficiaryAddress3(String beneficiaryAddress3) {
        this.beneficiaryAddress3 = beneficiaryAddress3;
    }

    public String getBeneficiaryTelephone() {
        return beneficiaryTelephone;
    }

    public void setBeneficiaryTelephone(String beneficiaryTelephone) {
        this.beneficiaryTelephone = beneficiaryTelephone;
    }

    public String getBeneficiaryCountry() {
        return beneficiaryCountry;
    }

    public void setBeneficiaryCountry(String beneficiaryCountry) {
        this.beneficiaryCountry = beneficiaryCountry;
    }

    public String getBeneficiaryCountryCode() {
        return beneficiaryCountryCode;
    }

    public void setBeneficiaryCountryCode(String beneficiaryCountryCode) {
        this.beneficiaryCountryCode = beneficiaryCountryCode;
    }

    public String getBeneficiaryBankCodeType() {
        return beneficiaryBankCodeType;
    }

    public void setBeneficiaryBankCodeType(String beneficiaryBankCodeType) {
        this.beneficiaryBankCodeType = beneficiaryBankCodeType;
    }

    public String getBeneficiaryBankCodeNumber() {
        return beneficiaryBankCodeNumber;
    }

    public void setBeneficiaryBankCodeNumber(String beneficiaryBankCodeNumber) {
        this.beneficiaryBankCodeNumber = beneficiaryBankCodeNumber;
    }

    public String getBeneficiaryBankName() {
        return beneficiaryBankName;
    }

    public void setBeneficiaryBankName(String beneficiaryBankName) {
        this.beneficiaryBankName = beneficiaryBankName;
    }

    public String getBeneficiaryBankAddress1() {
        return beneficiaryBankAddress1;
    }

    public void setBeneficiaryBankAddress1(String beneficiaryBankAddress1) {
        this.beneficiaryBankAddress1 = beneficiaryBankAddress1;
    }

    public String getBeneficiaryBankAddress2() {
        return beneficiaryBankAddress2;
    }

    public void setBeneficiaryBankAddress2(String beneficiaryBankAddress2) {
        this.beneficiaryBankAddress2 = beneficiaryBankAddress2;
    }

    public String getBeneficiaryBankAddress3() {
        return beneficiaryBankAddress3;
    }

    public void setBeneficiaryBankAddress3(String beneficiaryBankAddress3) {
        this.beneficiaryBankAddress3 = beneficiaryBankAddress3;
    }

    public String getBeneficiaryBankCountry() {
        return beneficiaryBankCountry;
    }

    public void setBeneficiaryBankCountry(String beneficiaryBankCountry) {
        this.beneficiaryBankCountry = beneficiaryBankCountry;
    }

    public String getBeneficiaryBankCountryCode() {
        return beneficiaryBankCountryCode;
    }

    public void setBeneficiaryBankCountryCode(String beneficiaryBankCountryCode) {
        this.beneficiaryBankCountryCode = beneficiaryBankCountryCode;
    }

    public String getIntermediaryBankCodeType() {
        return intermediaryBankCodeType;
    }

    public void setIntermediaryBankCodeType(String intermediaryBankCodeType) {
        this.intermediaryBankCodeType = intermediaryBankCodeType;
    }

    public String getIntermediaryBankCodeNumber() {
        return intermediaryBankCodeNumber;
    }

    public void setIntermediaryBankCodeNumber(String intermediaryBankCodeNumber) {
        this.intermediaryBankCodeNumber = intermediaryBankCodeNumber;
    }

    public String getIntermediaryBankName() {
        return intermediaryBankName;
    }

    public void setIntermediaryBankName(String intermediaryBankName) {
        this.intermediaryBankName = intermediaryBankName;
    }

    public String getIntermediaryBankAddress1() {
        return intermediaryBankAddress1;
    }

    public void setIntermediaryBankAddress1(String intermediaryBankAddress1) {
        this.intermediaryBankAddress1 = intermediaryBankAddress1;
    }

    public String getIntermediaryBankAddress2() {
        return intermediaryBankAddress2;
    }

    public void setIntermediaryBankAddress2(String intermediaryBankAddress2) {
        this.intermediaryBankAddress2 = intermediaryBankAddress2;
    }

    public String getIntermediaryBankAddress3() {
        return intermediaryBankAddress3;
    }

    public void setIntermediaryBankAddress3(String intermediaryBankAddress3) {
        this.intermediaryBankAddress3 = intermediaryBankAddress3;
    }

    public String getIntermediaryBankCountry() {
        return intermediaryBankCountry;
    }

    public String getIntermediaryBankCountryCode() {
        return intermediaryBankCountryCode;
    }

    public void setIntermediaryBankCountryCode(String intermediaryBankCountryCode) {
        this.intermediaryBankCountryCode = intermediaryBankCountryCode;
    }

    public void setIntermediaryBankCountry(String intermediaryBankCountry) {
        this.intermediaryBankCountry = intermediaryBankCountry;
    }

    public String getFurtherCreditAccount() {
        return furtherCreditAccount;
    }

    public void setFurtherCreditAccount(String furtherCreditAccount) {
        this.furtherCreditAccount = furtherCreditAccount;
    }

    public String getFurtherCreditName() {
        return furtherCreditName;
    }

    public void setFurtherCreditName(String furtherCreditName) {
        this.furtherCreditName = furtherCreditName;
    }

//    public Double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNombreTemplate() {
        return nombreTemplate;
    }

    public void setNombreTemplate(String nombreTemplate) {
        this.nombreTemplate = nombreTemplate;
    }

    public String getClaveTemporal() {
        return claveTemporal;
    }

    public void setClaveTemporal(String claveTemporal) {
        this.claveTemporal = claveTemporal;
    }

    public String getBeneficiaryBankCodeTypeSwift() {
        return beneficiaryBankCodeTypeSwift;
    }

    public void setBeneficiaryBankCodeTypeSwift(String beneficiaryBankCodeTypeSwift) {
        this.beneficiaryBankCodeTypeSwift = beneficiaryBankCodeTypeSwift;
    }

    public String getBeneficiaryBankCodeNumberSwift() {
        return beneficiaryBankCodeNumberSwift;
    }

    public void setBeneficiaryBankCodeNumberSwift(String beneficiaryBankCodeNumberSwift) {
        this.beneficiaryBankCodeNumberSwift = beneficiaryBankCodeNumberSwift;
    }

    public String getIntermediaryBankCodeTypeSwift() {
        return intermediaryBankCodeTypeSwift;
    }

    public void setIntermediaryBankCodeTypeSwift(String intermediaryBankCodeTypeSwift) {
        this.intermediaryBankCodeTypeSwift = intermediaryBankCodeTypeSwift;
    }

    public String getIntermediaryBankCodeNumberSwift() {
        return intermediaryBankCodeNumberSwift;
    }

    public void setIntermediaryBankCodeNumberSwift(String intermediaryBankCodeNumberSwift) {
        this.intermediaryBankCodeNumberSwift = intermediaryBankCodeNumberSwift;
    }
}
