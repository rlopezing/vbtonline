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
    private String beneficiaryLastName1;
    private String beneficiaryLastName2;
    private String beneficiaryType;
    private String beneficiaryTypePerson;
    private String beneficiaryAccountBank;
    private String beneficiaryAccount;
    private String beneficiaryClientId;
    private String beneficiaryEmail;
    private String beneficiaryAddress1;
    private String beneficiaryAddress2;
    private String beneficiaryAddress3;
    private String beneficiaryTelephone;
    private String beneficiaryCountry;
    private String beneficiaryCountryCode;
    private String beneficiaryBankCodeType;
    private String beneficiaryBankCodeNumber;
    private String beneficiaryBankCodeNumberABA;
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
    private String checkFM;
    private String verificarFM;
    private String motivo;
    private String statusAprobacion;
    private String beneficiaryCity;
    private String beneficiaryPostalCode;
    private String beneficiaryBankCity;
    private String intermediaryBankCity;
    private String origenTemplate;
    private String idTemplate;
    private String beneficiaryFullName;
    private String beneficiaryBirthDate;
    private String beneficiaryNationality;
    private String beneficiaryIdPassport;
    private String beneCountryIncorporation;
    private String sessionIdFolder;
    private String units;
    private String unidades;
    private String productType;
    private String productoTipo;
    private String cuentaTipo;
    private String accountType;
    private String verfUnits;
    private String isUnits;
    private String verfDesdeCuenta;
    private String verfFromAccount;
    private String codigoProductoDestino;
    private String destProductCode;
    private String carteraDest;
    private String cartDest;
    private String codigoProductoOrigen;
    private String oriProductCode;
    private String siglasProducto;
    private String siglasPro;
    private String monedaBLA;
    private String BLAMoneda;
    private String productCancelation;
    private String productoCancelacion;
    private String origen;
    private String origin;
    private String mismoDia;
    private String sameDay;
    private String productCancelationTOC;
    private String productoCancelacion2;
    private String precioUnits;
    private String unitsPrecio;

    public Object clone() {
        Object obj = null;
        try {
            obj = (SummaryTransfersToOtherBanksOd)super.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return obj;
    }

    public String getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(String idTemplate) {
        this.idTemplate = idTemplate;
    }

    public String getOrigenTemplate() {
        return origenTemplate;
    }

    public void setOrigenTemplate(String origenTemplate) {
        this.origenTemplate = origenTemplate;
    }

    public String getBeneficiaryTypePerson() {
        return beneficiaryTypePerson;
    }

    public void setBeneficiaryTypePerson(String beneficiaryTypePerson) {
        this.beneficiaryTypePerson = beneficiaryTypePerson;
    }

    public String getStatusAprobacion() {
        return statusAprobacion;
    }

    public String getBeneficiaryCity() {
        return beneficiaryCity;
    }

    public void setBeneficiaryCity(String beneficiaryCity) {
        this.beneficiaryCity = beneficiaryCity;
    }

    public String getBeneficiaryPostalCode() {
        return beneficiaryPostalCode;
    }

    public void setBeneficiaryPostalCode(String beneficiaryPostalCode) {
        this.beneficiaryPostalCode = beneficiaryPostalCode;
    }

    public String getBeneficiaryBankCity() {
        return beneficiaryBankCity;
    }

    public void setBeneficiaryBankCity(String beneficiaryBankCity) {
        this.beneficiaryBankCity = beneficiaryBankCity;
    }

    public String getIntermediaryBankCity() {
        return intermediaryBankCity;
    }

    public void setIntermediaryBankCity(String intermediaryBankCity) {
        this.intermediaryBankCity = intermediaryBankCity;
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

    public String getBeneficiaryClientId() {
        return beneficiaryClientId;
    }

    public void setBeneficiaryClientId(String beneficiaryClientId) {
        this.beneficiaryClientId = beneficiaryClientId;
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

    public String getBeneficiaryLastName1() {
        return beneficiaryLastName1;
    }

    public void setBeneficiaryLastName1(String beneficiaryLastName1) {
        this.beneficiaryLastName1 = beneficiaryLastName1;
    }

    public String getBeneficiaryLastName2() {
        return beneficiaryLastName2;
    }

    public void setBeneficiaryLastName2(String beneficiaryLastName2) {
        this.beneficiaryLastName2 = beneficiaryLastName2;
    }

    public String getBeneficiaryType() {
        return beneficiaryType;
    }

    public void setBeneficiaryType(String beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public void setBeneficiaryFullName(String beneficiaryFullName) {
        this.beneficiaryFullName = beneficiaryFullName;
    }

    public String getBeneficiaryBirthDate() {
        return beneficiaryBirthDate;
    }

    public void setBeneficiaryBirthDate(String beneficiaryBirthDate) {
        this.beneficiaryBirthDate = beneficiaryBirthDate;
    }

    public String getBeneficiaryNationality() {
        return beneficiaryNationality;
    }

    public void setBeneficiaryNationality(String beneficiaryNationality) {
        this.beneficiaryNationality = beneficiaryNationality;
    }

    public String getBeneficiaryIdPassport() {
        return beneficiaryIdPassport;
    }

    public void setBeneficiaryIdPassport(String beneficiaryIdPassport) {
        this.beneficiaryIdPassport = beneficiaryIdPassport;
    }

    public String getBeneCountryIncorporation() {
        return beneCountryIncorporation;
    }

    public void setBeneCountryIncorporation(String beneCountryIncorporation) {
        this.beneCountryIncorporation = beneCountryIncorporation;
    }

    public String getSessionIdFolder() {
        return sessionIdFolder;
    }

    public void setSessionIdFolder(String sessionIdFolder) {
        this.sessionIdFolder = sessionIdFolder;
    }

    public String getBeneficiaryBankCodeNumberABA() {
        return beneficiaryBankCodeNumberABA;
    }

    public void setBeneficiaryBankCodeNumberABA(String beneficiaryBankCodeNumberABA) {
        this.beneficiaryBankCodeNumberABA = beneficiaryBankCodeNumberABA;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getCheckFM() {
        return checkFM;
    }

    public void setCheckFM(String checkFM) {
        this.checkFM = checkFM;
    }

    public String getVerificarFM() {
        return verificarFM;
    }

    public void setVerificarFM(String verificarFM) {
        this.verificarFM = verificarFM;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductoTipo() {
        return productoTipo;
    }

    public void setProductoTipo(String productoTipo) {
        this.productoTipo = productoTipo;
    }

    public String getCuentaTipo() {
        return cuentaTipo;
    }

    public void setCuentaTipo(String cuentaTipo) {
        this.cuentaTipo = cuentaTipo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIsUnits() {
        return isUnits;
    }

    public void setIsUnits(String isUnits) {
        this.isUnits = isUnits;
    }

    public String getVerfUnits() {
        return verfUnits;
    }

    public void setVerfUnits(String verfUnits) {
        this.verfUnits = verfUnits;
    }

    public String getVerfDesdeCuenta() {
        return verfDesdeCuenta;
    }

    public void setVerfDesdeCuenta(String verfDesdeCuenta) {
        this.verfDesdeCuenta = verfDesdeCuenta;
    }

    public String getVerfFromAccount() {
        return verfFromAccount;
    }

    public void setVerfFromAccount(String verfFromAccount) {
        this.verfFromAccount = verfFromAccount;
    }

    public String getCarteraDest() {
        return carteraDest;
    }

    public void setCarteraDest(String carteraDest) {
        this.carteraDest = carteraDest;
    }

    public String getCartDest() {
        return cartDest;
    }

    public void setCartDest(String cartDest) {
        this.cartDest = cartDest;
    }

    public String getCodigoProductoDestino() {
        return codigoProductoDestino;
    }

    public void setCodigoProductoDestino(String codigoProductoDestino) {
        this.codigoProductoDestino = codigoProductoDestino;
    }

    public String getDestProductCode() {
        return destProductCode;
    }

    public void setDestProductCode(String destProductCode) {
        this.destProductCode = destProductCode;
    }

    public String getCodigoProductoOrigen() {
        return codigoProductoOrigen;
    }

    public void setCodigoProductoOrigen(String codigoProductoOrigen) {
        this.codigoProductoOrigen = codigoProductoOrigen;
    }

    public String getOriProductCode() {
        return oriProductCode;
    }

    public void setOriProductCode(String oriProductCode) {
        this.oriProductCode = oriProductCode;
    }

    public String getSiglasProducto() {
        return siglasProducto;
    }

    public void setSiglasProducto(String siglasProducto) {
        this.siglasProducto = siglasProducto;
    }

    public String getSiglasPro() {
        return siglasPro;
    }

    public void setSiglasPro(String siglasPro) {
        this.siglasPro = siglasPro;
    }

    public String getMonedaBLA() {
        return monedaBLA;
    }

    public void setMonedaBLA(String monedaBLA) {
        this.monedaBLA = monedaBLA;
    }

    public String getBLAMoneda() {
        return BLAMoneda;
    }

    public void setBLAMoneda(String BLAMoneda) {
        this.BLAMoneda = BLAMoneda;
    }

    public String getProductCancelation() {
        return productCancelation;
    }

    public void setProductCancelation(String productCancelation) {
        this.productCancelation = productCancelation;
    }

    public String getProductoCancelacion() {
        return productoCancelacion;
    }

    public void setProductoCancelacion(String productoCancelacion) {
        this.productoCancelacion = productoCancelacion;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMismoDia() {
        return mismoDia;
    }

    public void setMismoDia(String mismoDia) {
        this.mismoDia = mismoDia;
    }

    public String getSameDay() {
        return sameDay;
    }

    public void setSameDay(String sameDay) {
        this.sameDay = sameDay;
    }

    public String getProductoCancelacion2() {
        return productoCancelacion2;
    }

    public void setProductoCancelacion2(String productoCancelacion2) {
        this.productoCancelacion2 = productoCancelacion2;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getPrecioUnits() {
        return precioUnits;
    }

    public void setPrecioUnits(String precioUnits) {
        this.precioUnits = precioUnits;
    }

    public String getUnitsPrecio() {
        return unitsPrecio;
    }

    public void setUnitsPrecio(String unitsPrecio) {
        this.unitsPrecio = unitsPrecio;
    }

    public String getProductCancelationTOC() {
        return productCancelationTOC;
    }

    public void setProductCancelationTOC(String productCancelationTOC) {
        this.productCancelationTOC = productCancelationTOC;
    }
}
