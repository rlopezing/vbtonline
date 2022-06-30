package ve.com.vbtonline.servicio.od;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 07/04/11
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */

public class DataJson {
    private List<UsuarioOd> usuarios;
    private List<BackOfficeOd> backOffices;
    private List<CreditCardsOd> creditCardss;
    private List<MutualFundsOd> mutualFundss;
    private List<AccountsOd> accountss;
    private List<AllMyPortafolioOd> allMyPortafolios;
    private List<MyInformationOd> myInformations;
    private List<PortafolioOd> portafolios;
    private List<TimeDepositsOd> timeDepositss;
    private List<TransfersOd> transferss;
    private List<ItemOd> menu;
    private List<VBTUsersOd> logins;
    private List<CtaOpcOd> opcionesGruposs;
    private List<SummaryTransfersToOtherBanksOd> resumenTOBs;
    private List<ConsultUsersOd> consultaUsuarioss;
    private List<ConsultaBitacoraOd> consultaBitacorass;
    private List<ConsultContratsOd> consultaContratoss;
    private List<GruposOd> consultaGrupoOS;
    private List<OpcionesOd> consultaGrupoOP;
    private List<VBTContratoOd> consultaEditarContratos;
    private List<String> parametros;
    private List<ParametrosPersonalesOd> parametrosPersonales;
    private List<ParametrosPersonalesOd> parametrosGlobales;
    private List<ParametrosPersonalesFCOd> parametrosPersonalesFC;
    private List<CarterasOd> listaCarteras;
    private List<VBTUsersOd> listaUsuarios;
    private List<ContratosOd> contratos;


    public List<ParametrosPersonalesOd> getParametrosGlobales() {
        return parametrosGlobales;
    }

    public void setParametrosGlobales(List<ParametrosPersonalesOd> parametrosGlobales) {
        this.parametrosGlobales = parametrosGlobales;
    }

    public List<SummaryTransfersToOtherBanksOd> getResumenTOBs() {
        return resumenTOBs;
    }

    public void setResumenTOBs(List<SummaryTransfersToOtherBanksOd> resumenTOBs) {
        this.resumenTOBs = resumenTOBs;
    }

    public List<CtaOpcOd> getOpcionesGruposs() {
        return opcionesGruposs;
    }

    public void setOpcionesGruposs(List<CtaOpcOd> opcionesGruposs) {
        this.opcionesGruposs = opcionesGruposs;
    }

    public List<ItemOd> getMenu() {
        return menu;
    }

    public void setMenu(List<ItemOd> menu) {
        this.menu = menu;
    }

    public List<CreditCardsOd> getCreditCardss() {
        return creditCardss;
    }

    public void setCreditCardss(List<CreditCardsOd> creditCardss) {
        this.creditCardss = creditCardss;
    }

    public List<MutualFundsOd> getMutualFundss() {
        return mutualFundss;
    }

    public void setMutualFundss(List<MutualFundsOd> mutualFundss) {
        this.mutualFundss = mutualFundss;
    }

    public List<AccountsOd> getAccountss() {
        return accountss;
    }

    public void setAccountss(List<AccountsOd> accountss) {
        this.accountss = accountss;
    }

    public List<AllMyPortafolioOd> getAllMyPortafolios() {
        return allMyPortafolios;
    }

    public void setAllMyPortafolios(List<AllMyPortafolioOd> allMyPortafolios) {
        this.allMyPortafolios = allMyPortafolios;
    }

    public List<MyInformationOd> getMyInformations() {
        return myInformations;
    }

    public void setMyInformations(List<MyInformationOd> myInformations) {
        this.myInformations = myInformations;
    }

    public List<PortafolioOd> getPortafolios() {
        return portafolios;
    }

    public void setPortafolios(List<PortafolioOd> portafolios) {
        this.portafolios = portafolios;
    }

    public List<TimeDepositsOd> getTimeDepositss() {
        return timeDepositss;
    }

    public void setTimeDepositss(List<TimeDepositsOd> timeDepositss) {
        this.timeDepositss = timeDepositss;
    }

    public List<BackOfficeOd> getBackOffices() {
        return backOffices;
    }

    public void setBackOffices(List<BackOfficeOd> backOffices) {
        this.backOffices = backOffices;
    }

    public List<UsuarioOd> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioOd> usuarios) {
        this.usuarios = usuarios;
    }

    public List<TransfersOd> getTransferss() {
        return transferss;
    }

    public void setTransferss(List<TransfersOd> transferss) {
        this.transferss = transferss;
    }

    public List<VBTUsersOd> getLogins() {
        return logins;
    }

    public void setLogins(List<VBTUsersOd> logins) {
        this.logins = logins;
    }

    public List<ConsultUsersOd> getConsultaUsuarioss() {
        return consultaUsuarioss;
    }

    public void setConsultaUsuarioss(List<ConsultUsersOd> consultaUsuarioss) {
        this.consultaUsuarioss = consultaUsuarioss;
    }

    public List<ConsultContratsOd> getConsultaContratoss() {
        return consultaContratoss;
    }

    public void setConsultaContratoss(List<ConsultContratsOd> consultaContratoss) {
        this.consultaContratoss = consultaContratoss;
    }

    public List<GruposOd> getConsultaGrupoOS() {
        return consultaGrupoOS;
    }

    public void setConsultaGrupoOS(List<GruposOd> consultaGrupoOS) {
        this.consultaGrupoOS = consultaGrupoOS;
    }

    public List<OpcionesOd> getConsultaGrupoOP() {
        return consultaGrupoOP;
    }

    public void setConsultaGrupoOP(List<OpcionesOd> consultaGrupoOP) {
        this.consultaGrupoOP = consultaGrupoOP;
    }

    public List<VBTContratoOd> getConsultaEditarContratos() {
        return consultaEditarContratos;
    }

    public void setConsultaEditarContratos(List<VBTContratoOd> consultaEditarContratos) {
        this.consultaEditarContratos = consultaEditarContratos;
    }

    public List<String> getParametros() {
        return parametros;
    }

    public void setParametros(List<String> parametros) {
        this.parametros = parametros;
    }

    public List<ParametrosPersonalesOd> getParametrosPersonales() {
        return parametrosPersonales;
    }

    public void setParametrosPersonales(List<ParametrosPersonalesOd> parametrosPersonales) {
        this.parametrosPersonales = parametrosPersonales;
    }

    public List<ConsultaBitacoraOd> getConsultaBitacorass() {
        return consultaBitacorass;
    }

    public void setConsultaBitacorass(List<ConsultaBitacoraOd> consultaBitacorass) {
        this.consultaBitacorass = consultaBitacorass;
    }

    public List<CarterasOd> getListaCarteras() {
        return listaCarteras;
    }

    public void setListaCarteras(List<CarterasOd> listaCarteras) {
        this.listaCarteras = listaCarteras;
    }

    public List<VBTUsersOd> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<VBTUsersOd> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<ContratosOd> getContratos() {
        return contratos;
    }

    public void setContratos(List<ContratosOd> contratos) {
        this.contratos = contratos;
    }

    public List<ParametrosPersonalesFCOd> getParametrosPersonalesFC() {
        return parametrosPersonalesFC;
    }

    public void setParametrosPersonalesFC(List<ParametrosPersonalesFCOd> parametrosPersonalesFC) {
        this.parametrosPersonalesFC = parametrosPersonalesFC;
    }
}