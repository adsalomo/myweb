/**
 * @autor AdrianL
 * @since 19 dic 2016
 * @description configuracion de servicios rest
 */

/**
 * Obtiene la url de servicio rest
 * @param {type} nameUrl
 * @returns {undefined}
 */
function obtenerUrlService(nameUrl) {
    var url = '';
    var baseServicios = window.location.protocol + '//' + window.location.hostname + ':' + '8081' + '/';
    switch (nameUrl) {
        case 'GetStructure':
            url = baseServicios + 'web/elenaschool/getEstructuraTabla';
            break;
        case 'GetQuery':
            url = baseServicios + 'web/elenaschool/getConsulta';
            break;
        case 'UpdateModel':
            url = baseServicios + 'web/elenaschool/updateModel';
            break;
        default:
            url = baseServicios + 'web/elenaschool/myWeb';
            break;
    }
    return url;
}


