package ve.com.vbtonline.servicio.od;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class OrdenTransferenciaOd implements Serializable {
    private int num_instruccion;
    private String num_contrato;
    private String codcar;
    private String estatus_instruccion;
    private Calendar fecha_estatus;
    private String bof00_codmov;
    private Calendar bof02_as_of_date;
    private String bof02_as_of_time;
    private String bof03_currency_code;
    private int bof16_amount;
    private String bof00_codemp;
    private String bof00_codcar;
    private String bof00_codcol;
    private String bof00_codinst;
    private String beneficiary_type;
    private String beneficiary_type_number;
    private String beneficiary_description;
    private String beneficiary_bank_type;
    private String beneficiary_bank_type_number;
    private String beneficiary_bank_description;
    private String intermediary_bank_type;
    private String intermediary_bank_type_number;
    private String intermediary_bank_description;
    private String originators_bank_type;
    private String originators_bank_type_number;
    private String originators_bank_description;
    private String beneficiary_info;
    private String originators_info;
    private String reciever_info;
    private String charge_to;
    private String trnseq;
    private String statws;
    private String seqnbr;
    private String swift;
    private String codtipomov;
    private String tipocredito;
    private String source;
    private String codemp_origen;
    private String codcar_origen;
    private String codcol_origen;
    private String codinst_origen;
    private String codtipomov_bofa;
    private String refbanmov;
    private String beneficiario;
    private String observ;
    private String cirif;
    private String usrid_carga;
    private String usrid_aprueba;
    private String usrid_libera;
    private Calendar fecha_carga;
    private Calendar fecha_aprueba;
    private Calendar fecha_libera;
    private String email_beneficiario;
    private String email_origen;
    private String motivo_estatus;
    private String bof16_bank_reference_number;
    private String telefono_beneficiario;
    private String codemp;
    private String codcol;
    private String codinst;
    private String beneficiary_name;
    private String beneficiary_bank_name;
    private String intermediary_bank_name;
    private String originators_name;
    private String details_of_payment_name;
    private String details_of_payment_description;
    private String codmon_credit;
    private String ffc_number;
    private String ffc_name;

    public int getNum_instruccion() {
        return num_instruccion;
    }

    public void setNum_instruccion(int num_instruccion) {
        this.num_instruccion = num_instruccion;
    }

    public String getNum_contrato() {
        return num_contrato;
    }

    public void setNum_contrato(String num_contrato) {
        this.num_contrato = num_contrato;
    }

    public String getCodcar() {
        return codcar;
    }

    public void setCodcar(String codcar) {
        this.codcar = codcar;
    }

    public String getEstatus_instruccion() {
        return estatus_instruccion;
    }

    public void setEstatus_instruccion(String estatus_instruccion) {
        this.estatus_instruccion = estatus_instruccion;
    }

    public Calendar getFecha_estatus() {
        return fecha_estatus;
    }

    public void setFecha_estatus(Calendar fecha_estatus) {
        this.fecha_estatus = fecha_estatus;
    }

    public String getBof00_codmov() {
        return bof00_codmov;
    }

    public void setBof00_codmov(String bof00_codmov) {
        this.bof00_codmov = bof00_codmov;
    }

    public Calendar getBof02_as_of_date() {
        return bof02_as_of_date;
    }

    public void setBof02_as_of_date(Calendar bof02_as_of_date) {
        this.bof02_as_of_date = bof02_as_of_date;
    }

    public String getBof02_as_of_time() {
        return bof02_as_of_time;
    }

    public void setBof02_as_of_time(String bof02_as_of_time) {
        this.bof02_as_of_time = bof02_as_of_time;
    }

    public String getBof03_currency_code() {
        return bof03_currency_code;
    }

    public void setBof03_currency_code(String bof03_currency_code) {
        this.bof03_currency_code = bof03_currency_code;
    }

    public int getBof16_amount() {
        return bof16_amount;
    }

    public void setBof16_amount(int bof16_amount) {
        this.bof16_amount = bof16_amount;
    }

    public String getBof00_codemp() {
        return bof00_codemp;
    }

    public void setBof00_codemp(String bof00_codemp) {
        this.bof00_codemp = bof00_codemp;
    }

    public String getBof00_codcar() {
        return bof00_codcar;
    }

    public void setBof00_codcar(String bof00_codcar) {
        this.bof00_codcar = bof00_codcar;
    }

    public String getBof00_codcol() {
        return bof00_codcol;
    }

    public void setBof00_codcol(String bof00_codcol) {
        this.bof00_codcol = bof00_codcol;
    }

    public String getBof00_codinst() {
        return bof00_codinst;
    }

    public void setBof00_codinst(String bof00_codinst) {
        this.bof00_codinst = bof00_codinst;
    }

    public String getBeneficiary_type() {
        return beneficiary_type;
    }

    public void setBeneficiary_type(String beneficiary_type) {
        this.beneficiary_type = beneficiary_type;
    }

    public String getBeneficiary_type_number() {
        return beneficiary_type_number;
    }

    public void setBeneficiary_type_number(String beneficiary_type_number) {
        this.beneficiary_type_number = beneficiary_type_number;
    }

    public String getBeneficiary_description() {
        return beneficiary_description;
    }

    public void setBeneficiary_description(String beneficiary_description) {
        this.beneficiary_description = beneficiary_description;
    }

    public String getBeneficiary_bank_type() {
        return beneficiary_bank_type;
    }

    public void setBeneficiary_bank_type(String beneficiary_bank_type) {
        this.beneficiary_bank_type = beneficiary_bank_type;
    }

    public String getBeneficiary_bank_type_number() {
        return beneficiary_bank_type_number;
    }

    public void setBeneficiary_bank_type_number(String beneficiary_bank_type_number) {
        this.beneficiary_bank_type_number = beneficiary_bank_type_number;
    }

    public String getBeneficiary_bank_description() {
        return beneficiary_bank_description;
    }

    public void setBeneficiary_bank_description(String beneficiary_bank_description) {
        this.beneficiary_bank_description = beneficiary_bank_description;
    }

    public String getIntermediary_bank_type() {
        return intermediary_bank_type;
    }

    public void setIntermediary_bank_type(String intermediary_bank_type) {
        this.intermediary_bank_type = intermediary_bank_type;
    }

    public String getIntermediary_bank_type_number() {
        return intermediary_bank_type_number;
    }

    public void setIntermediary_bank_type_number(String intermediary_bank_type_number) {
        this.intermediary_bank_type_number = intermediary_bank_type_number;
    }

    public String getIntermediary_bank_description() {
        return intermediary_bank_description;
    }

    public void setIntermediary_bank_description(String intermediary_bank_description) {
        this.intermediary_bank_description = intermediary_bank_description;
    }

    public String getOriginators_bank_type() {
        return originators_bank_type;
    }

    public void setOriginators_bank_type(String originators_bank_type) {
        this.originators_bank_type = originators_bank_type;
    }

    public String getOriginators_bank_type_number() {
        return originators_bank_type_number;
    }

    public void setOriginators_bank_type_number(String originators_bank_type_number) {
        this.originators_bank_type_number = originators_bank_type_number;
    }

    public String getOriginators_bank_description() {
        return originators_bank_description;
    }

    public void setOriginators_bank_description(String originators_bank_description) {
        this.originators_bank_description = originators_bank_description;
    }

    public String getBeneficiary_info() {
        return beneficiary_info;
    }

    public void setBeneficiary_info(String beneficiary_info) {
        this.beneficiary_info = beneficiary_info;
    }

    public String getOriginators_info() {
        return originators_info;
    }

    public void setOriginators_info(String originators_info) {
        this.originators_info = originators_info;
    }

    public String getReciever_info() {
        return reciever_info;
    }

    public void setReciever_info(String reciever_info) {
        this.reciever_info = reciever_info;
    }

    public String getCharge_to() {
        return charge_to;
    }

    public void setCharge_to(String charge_to) {
        this.charge_to = charge_to;
    }

    public String getTrnseq() {
        return trnseq;
    }

    public void setTrnseq(String trnseq) {
        this.trnseq = trnseq;
    }

    public String getStatws() {
        return statws;
    }

    public void setStatws(String statws) {
        this.statws = statws;
    }

    public String getSeqnbr() {
        return seqnbr;
    }

    public void setSeqnbr(String seqnbr) {
        this.seqnbr = seqnbr;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getCodtipomov() {
        return codtipomov;
    }

    public void setCodtipomov(String codtipomov) {
        this.codtipomov = codtipomov;
    }

    public String getTipocredito() {
        return tipocredito;
    }

    public void setTipocredito(String tipocredito) {
        this.tipocredito = tipocredito;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCodemp_origen() {
        return codemp_origen;
    }

    public void setCodemp_origen(String codemp_origen) {
        this.codemp_origen = codemp_origen;
    }

    public String getCodcar_origen() {
        return codcar_origen;
    }

    public void setCodcar_origen(String codcar_origen) {
        this.codcar_origen = codcar_origen;
    }

    public String getCodcol_origen() {
        return codcol_origen;
    }

    public void setCodcol_origen(String codcol_origen) {
        this.codcol_origen = codcol_origen;
    }

    public String getCodinst_origen() {
        return codinst_origen;
    }

    public void setCodinst_origen(String codinst_origen) {
        this.codinst_origen = codinst_origen;
    }

    public String getCodtipomov_bofa() {
        return codtipomov_bofa;
    }

    public void setCodtipomov_bofa(String codtipomov_bofa) {
        this.codtipomov_bofa = codtipomov_bofa;
    }

    public String getRefbanmov() {
        return refbanmov;
    }

    public void setRefbanmov(String refbanmov) {
        this.refbanmov = refbanmov;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getObserv() {
        return observ;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public String getCirif() {
        return cirif;
    }

    public void setCirif(String cirif) {
        this.cirif = cirif;
    }

    public String getUsrid_carga() {
        return usrid_carga;
    }

    public void setUsrid_carga(String usrid_carga) {
        this.usrid_carga = usrid_carga;
    }

    public String getUsrid_aprueba() {
        return usrid_aprueba;
    }

    public void setUsrid_aprueba(String usrid_aprueba) {
        this.usrid_aprueba = usrid_aprueba;
    }

    public String getUsrid_libera() {
        return usrid_libera;
    }

    public void setUsrid_libera(String usrid_libera) {
        this.usrid_libera = usrid_libera;
    }

    public Calendar getFecha_carga() {
        return fecha_carga;
    }

    public void setFecha_carga(Calendar fecha_carga) {
        this.fecha_carga = fecha_carga;
    }

    public Calendar getFecha_aprueba() {
        return fecha_aprueba;
    }

    public void setFecha_aprueba(Calendar fecha_aprueba) {
        this.fecha_aprueba = fecha_aprueba;
    }

    public Calendar getFecha_libera() {
        return fecha_libera;
    }

    public void setFecha_libera(Calendar fecha_libera) {
        this.fecha_libera = fecha_libera;
    }

    public String getEmail_beneficiario() {
        return email_beneficiario;
    }

    public void setEmail_beneficiario(String email_beneficiario) {
        this.email_beneficiario = email_beneficiario;
    }

    public String getEmail_origen() {
        return email_origen;
    }

    public void setEmail_origen(String email_origen) {
        this.email_origen = email_origen;
    }

    public String getMotivo_estatus() {
        return motivo_estatus;
    }

    public void setMotivo_estatus(String motivo_estatus) {
        this.motivo_estatus = motivo_estatus;
    }

    public String getBof16_bank_reference_number() {
        return bof16_bank_reference_number;
    }

    public void setBof16_bank_reference_number(String bof16_bank_reference_number) {
        this.bof16_bank_reference_number = bof16_bank_reference_number;
    }

    public String getTelefono_beneficiario() {
        return telefono_beneficiario;
    }

    public void setTelefono_beneficiario(String telefono_beneficiario) {
        this.telefono_beneficiario = telefono_beneficiario;
    }

    public String getCodemp() {
        return codemp;
    }

    public void setCodemp(String codemp) {
        this.codemp = codemp;
    }

    public String getCodcol() {
        return codcol;
    }

    public void setCodcol(String codcol) {
        this.codcol = codcol;
    }

    public String getCodinst() {
        return codinst;
    }

    public void setCodinst(String codinst) {
        this.codinst = codinst;
    }

    public String getBeneficiary_name() {
        return beneficiary_name;
    }

    public void setBeneficiary_name(String beneficiary_name) {
        this.beneficiary_name = beneficiary_name;
    }

    public String getBeneficiary_bank_name() {
        return beneficiary_bank_name;
    }

    public void setBeneficiary_bank_name(String beneficiary_bank_name) {
        this.beneficiary_bank_name = beneficiary_bank_name;
    }

    public String getIntermediary_bank_name() {
        return intermediary_bank_name;
    }

    public void setIntermediary_bank_name(String intermediary_bank_name) {
        this.intermediary_bank_name = intermediary_bank_name;
    }

    public String getOriginators_name() {
        return originators_name;
    }

    public void setOriginators_name(String originators_name) {
        this.originators_name = originators_name;
    }

    public String getDetails_of_payment_name() {
        return details_of_payment_name;
    }

    public void setDetails_of_payment_name(String details_of_payment_name) {
        this.details_of_payment_name = details_of_payment_name;
    }

    public String getDetails_of_payment_description() {
        return details_of_payment_description;
    }

    public void setDetails_of_payment_description(String details_of_payment_description) {
        this.details_of_payment_description = details_of_payment_description;
    }

    public String getCodmon_credit() {
        return codmon_credit;
    }

    public void setCodmon_credit(String codmon_credit) {
        this.codmon_credit = codmon_credit;
    }

    public String getFfc_number() {
        return ffc_number;
    }

    public void setFfc_number(String ffc_number) {
        this.ffc_number = ffc_number;
    }

    public String getFfc_name() {
        return ffc_name;
    }

    public void setFfc_name(String ffc_name) {
        this.ffc_name = ffc_name;
    }
}
