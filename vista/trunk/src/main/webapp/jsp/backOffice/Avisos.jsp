<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
<div id="div_consultarAvisos" class="div_tabla_consulta">

    <div>
        <h1 id="Titulo_Avisos">Avisos</h1>
    </div>
    <fieldset class="div_consulta 0000000004_1">
        <input type="button" id="agregarAvisos" href="#" value="Agregar Aviso" class="botonGrandeDerecha">
    </fieldset>
<fieldset class="div_consulta">
    <legend>Buscar</legend>
    <div id="avisosBO">
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
                <td class="datos2"><label class="datos">Tipo de aviso</label></td>
                <td><select id="tipoAvisoBO" class="selectFormulario" title="Tipo Código Banco" > </select></td>
                <td  class="datos2"><label class="datos">Estatus:</label></td>
                <td><select id="estatusAvisoBO" class="selectFormulario" title="Estatus" > </select></td>
                <td width="30%"></td>
            </tr>
            <tr>
                <td class="datos2"><label class="datos">Desde</label></td>
                <td>
                    <input type="text" tabindex="2" class="inputFormulario fechadias" id="fechaDesdeFiltroAviso" title="Desde">
                    <label class="datos">dd/mm/yyyy</label></td>
                <td class="datos2"><label class="datos">Hasta</label></td>
                <td>
                    <input type="text" tabindex="2" class="inputFormulario fechadias" id="fechaHastaFiltrAviso" title="Hasta">
                    <label class="datos">dd/mm/yyyy</label></td>

                <td width="30%"></td>
            </tr>
            <tr>
                <td colspan="6" align="right">
                    <!-- <input type='button' value="Agregar" class="botonGrandeDerecha"/> -->
                    <input type='button' id="limpiarFiltroAvisos" href="#" value="Limpiar" class="boton"/>
                    <input type='button' id="buscarAvisos" href="#" value="Buscar" class="boton"/>
                </td>
            </tr>
        </table>
    </div>

</fieldset>
<fieldset class="div_consulta">

    <div id="div_tabla_avisos_bo" class="div_tabla_bitacora">
    </div>
    <div id="paginacion_tabla_consulta_avisos_bo" class="result"></div>
</fieldset>
</div>



<div id="div_Avisos_BO" class="div_tabla_consulta" style="display:none;">
    <div>
        <h1 id="title_opc_aviso">Crear Aviso</h1>
    </div>
    <fieldset class="div_consulta">
        <input type="button" class="boton" value="Agregar" id="avisoGuardar">
        <input type="button" class="boton" value="Limpiar" id="avisoLimpiar">
        <input type="button" class="botonDerecha" value="&lt;&lt; Volver" id="avisoVolver">
    </fieldset>
    <fieldset class="div_consulta" id="datosAvisoAdd">
        <s:form action="BackOffice_crearAviso" method="post" id="frmCrearAviso" target="_blank" enctype="multipart/form-data">
        <table cellspacing="2" cellpadding="2" summary="tabla">
            <tr>
                <td class="datos2">
                    Tipo de aviso
                </td>
                <td width="21%">
                    <select name="AddTipoAvisoBO" id="AddTipoAvisoBO" title="Tipo Aviso" class="selectFormulario obligatorioAviso" disabled>
                    </select>
                </td>
                <td class="datos2">
                    Descripción
                </td>
                <td>
                    <input type="text" name="addTextoAviso" id="addTextoAviso" class="obligatorioAviso inputFormulario" title="Descripcion" style="width:195px"  size="17">
                    <input type="hidden" id="codigoAviso" name="codigoAviso">
                </td>
            </tr>
            <tr>
                <td class="datos2">
                    <label class="datos">Desde</label>
                </td>
                <td>
                    <input type="text" title="Fecha Inicio" name="fechaInicioAviso" id="fechaInicioAviso" class="inputFormulario obligatorioAviso" tabindex="2">
                    <label class="datos">dd/mm/yyyy</label>
                </td>
                <td class="datos2">
                    <label class="datos">Hasta</label>
                </td>
                <td>
                    <input type="text" title="Fecha Fin" name="fechaFinAviso" id="fechaFinAviso" class="inputFormulario obligatorioAviso" tabindex="2">
                    <label class="datos">dd/mm/yyyy &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*Período de tiempo en el cual estará el aviso en la página de bienvenida</label>
                </td>
            </tr>
            <tr>
                <td class="datos2">
                    Estatus
                </td>
                <td>
                    <select name="AddStatusAvisoBO" id="AddStatusAvisoBO" title="Estatus" class="selectFormulario obligatorioAviso" disabled >
                    </select>
                </td>
                <td>
                    <label class="datos2">Imagen de fondo</label>
                </td>
                <td>
                    <div class="custom-input-file inputFormulario">
                        <table width="100%">
                             <tr>
                                 <td width="36px">
                                     <div class="upload">
                                         <input type="file" id="uploadImagenAviso" name="uploadImagenAviso" title="Imagen"/>
                                     </div>
                                     <input type="hidden" id="imagenAvisoBase64">
                                 </td>
                                 <td>
                                     <label class="datos">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*Solo se permiten imágenes en formato jpg o jpeg</label>
                                 </td>
                             </tr>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="datos2" style="padding-top:20px;">
                    <input type="hidden"  name="textoAvisoEsp" id="textoAvisoEsp" title="Texto Español" class="inputFormulario" tabindex="2">
                </td>
                <td colspan="5" rowspan="2" style="padding-top:20px;">
                    <table width="514px" align="center">
                        <tr>
                            <td valign="middle">
                                <table width="100%">
                                    <tr>
                                        <td>
                                            <label class="datos" id="textoTituloAvisoEsp" style="cursor:pointer" onClick="editorActual('esp');">Texto Español</label>
                                        </td>
                                        <td style="border-left: 2px dotted #ccc; padding-left: 25px;">
                                            <label class="datos"  id="textoTituloAvisoIng"  style="cursor:pointer"  onClick="editorActual('ing');">Texto Inglés</label>
                                        </td>
                                        <td width="20%">
                                        </td>
                                        <td align="right">
                                            <label class="datos"  style="cursor:pointer">Vista previa</label>
                                        </td>
                                        <td align="right">
                                            <img id="vistaPreviaAviso" style="cursor:pointer" title="Vista Previa" width="25px" src="../vbtonline/resources/images/pre2.jpg">
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td> <textarea id="editorAviso" rows="2" cols="2">
                            </textarea></td>
                        </tr>
                    </table>

                </td>
            </tr>
            <tr>
                <td class="datos2" >
                    <input type="hidden" name="textoAvisoIng"  id="textoAvisoIng" title="Texto Inglés" class="inputFormulario" tabindex="2">
                </td>
                <td colspan="2">

                </td>
            </tr>
        </table>
               <input type="hidden" name="operacionAviso"  id="operacionAviso">
               <input type="hidden" name="cambioImagen"  id="cambioImagen">

        </s:form>
    </fieldset>

    <div id="detalle_aviso" style="display: none;">
        <table width="50%" align="center">
            <tr>
                <td>
                    <label class="datos" id="textoTituloAvisoEspPrev" style="cursor:pointer" onClick="editorActualPrev('esp');">Texto Español</label>
                </td>
                <td style="border-left: 2px dotted #ccc; padding-left: 25px;">
                    <label class="datos"  id="textoTituloAvisoIngPrev"  style="cursor:pointer"  onClick="editorActualPrev('ing');">Texto Ingles</label>
                </td>
           </tr>
        </table>

        <table align="center">
            <tr>
                <div id="imagePreviewAviso" style=""></div>
            </tr>
            <tr>
                <td class="datos_resumen">
                    <input type="button" class="boton" value="Cerrar" id="btn_cerrar_vista_previa">
                </td>

            </tr>
        </table>
    </div>
</div>
--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="Titulo_consultarUsuariosFC" class="banner__title banner__title--modifier">
                Avisos
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome44" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>MANTENIMIENTO</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>AVISOS</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_consultarUsuariosFC" class="banner__title banner__title--modifier">
                Avisos
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome44" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>MANTENIMIENTO</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>AVISOS</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_consultarAvisos" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                    <input type="button" id="agregarAvisos" value="Agregar Aviso" class="section__button button">
                </div>
            </div>
            <div class="section__content">
                <div id="avisosBO" class="form-filter">
                    <span class="form-filter__title">Buscar</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="tipoAvisoBO">Tipo de aviso</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="tipoAvisoBO"
                                        title="Tipo Código Banco"
                                        class="select-section__select select-section__select--form selectFormulario"
                                >
                                </select>
                                <img
                                        class="select-section__icon select-section__icon--form"
                                        src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                        alt=""
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="estatusAvisoBO">Estatus:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="estatusAvisoBO"
                                        title="Estatus"
                                        class="select-section__select select-section__select--form selectFormulario"
                                >
                                </select>
                                <img
                                        class="select-section__icon select-section__icon--form"
                                        src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                        alt=""
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="fechaDesdeFiltroAviso">Desde</label>
                            <input
                                    id="fechaDesdeFiltroAviso"
                                    title="Desde"
                                    type="text"
                                    placeholder="dd/mm/yyyy"
                                    class="form-filter__input input input--form inputFormulario fechadias"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="fechaHastaFiltrAviso">Hasta</label>
                            <input
                                    id="fechaHastaFiltrAviso"
                                    title="Hasta"
                                    type="text"
                                    placeholder="dd/mm/yyyy"
                                    class="form-filter__input input input--form inputFormulario fechadias"
                            />
                        </div>
                    </div>
                    <div class="form-filter__buttons">
                        <input
                                id="limpiarFiltroAvisos"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Limpiar"
                        />
                        <input
                                id="buscarAvisos"
                                type="button"
                                class="form-filter__button button"
                                value="Buscar"
                        />
                    </div>
                </div>
                <div class="table">
                    <div id="div_tabla_avisos_bo">

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_Avisos_BO" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="title_opc_aviso" class="section__title">Crear Aviso</span>
                    <input type="button" class="section__button button button--white" value="<< Volver" id="avisoVolver">
                </div>
            </div>
            <div id="datosAvisoAdd" class="section__content">
                <s:form action="BackOffice_crearAviso.action" method="post" id="frmCrearAviso" target="_blank" cssClass="form-filter" enctype="multipart/form-data">
                    <span class="form-filter__title">Buscar</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="AddTipoAvisoBO">Tipo de aviso</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="AddTipoAvisoBO"
                                        name="AddTipoAvisoBO"
                                        title="Tipo Código Banco"
                                        class="select-section__select select-section__select--form selectFormulario obligatorioAviso"
                                        disabled
                                >
                                </select>
                                <img
                                        class="select-section__icon select-section__icon--form"
                                        src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                        alt=""
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="addTextoAviso">Descripción</label>
                            <input
                                    id="addTextoAviso"
                                    name="addTextoAviso"
                                    title="Descripcion"
                                    type="text"
                                    size="17"
                                    class="form-filter__input input input--form obligatorioAviso inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="fechaInicioAviso">Desde</label>
                            <input
                                    id="fechaInicioAviso"
                                    name="fechaInicioAviso"
                                    title="Fecha Inicio"
                                    type="text"
                                    placeholder="dd/mm/yyyy"
                                    class="form-filter__input input input--form inputFormulario obligatorioAviso"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="fechaFinAviso">Hasta</label>
                            <input
                                    id="fechaFinAviso"
                                    name="fechaFinAviso"
                                    title="Fecha Fin"
                                    type="text"
                                    placeholder="dd/mm/yyyy"
                                    class="form-filter__input input input--form inputFormulario obligatorioAviso"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="AddStatusAvisoBO">Estatus</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="AddStatusAvisoBO"
                                        name="AddStatusAvisoBO"
                                        title="Estatus"
                                        class="select-section__select select-section__select--form selectFormulario obligatorioAviso"
                                        disabled
                                >
                                </select>
                                <img
                                        class="select-section__icon select-section__icon--form"
                                        src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                        alt=""
                                />
                            </div>
                        </div>
                        <div class="form-filter__item form-filter__item--full">
                            <label id="textoTituloAvisoEsp" style="cursor:pointer" onClick="editorActual('esp');">Texto Español</label>
                            <label id="textoTituloAvisoIng"  style="cursor:pointer"  onClick="editorActual('ing');">Texto Inglés</label>
                            <img id="vistaPreviaAviso" style="cursor:pointer" title="Vista Previa" width="25px" src="../vbtonline/resources/images/pre2.jpg">
                            <textarea id="editorAviso" rows="2" cols="2">
                            </textarea>
                        </div>
                    </div>
                    <input type="hidden" id="imagenAvisoBase64">
                    <input type="hidden"  name="textoAvisoEsp" id="textoAvisoEsp" title="Texto Español" class="inputFormulario" tabindex="2">
                    <input type="hidden" name="textoAvisoIng"  id="textoAvisoIng" title="Texto Inglés" class="inputFormulario" tabindex="2">
                    <input type="hidden" name="operacionAviso"  id="operacionAviso">
                    <input type="hidden" name="cambioImagen"  id="cambioImagen">
                    <div class="form-filter__buttons">
                        <input type="button" class="button button--white" value="Limpiar" id="avisoLimpiar">
                        <input type="button" class="button" value="Agregar" id="avisoGuardar">
                    </div>
                </s:form>
            </div>
        </div>
    </div>
</main>
