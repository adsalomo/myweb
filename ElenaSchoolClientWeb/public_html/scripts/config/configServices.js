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
    var serviceBase = window.location.protocol + '//' + window.location.hostname + ':' + '8081' + '/';
    switch (nameUrl) {
        case 'GetStructure':
            url = serviceBase + 'web/elenaschool/getStructureTable';
            break;
        case 'GetQuery':
            url = serviceBase + 'web/elenaschool/getQuery';
            break;
        case 'UpdateModel':
            url = serviceBase + 'web/elenaschool/updateModel';
            break;
        case 'GetMaxCode':
            url = serviceBase + 'web/elenaschool/getMaxCode';
            break;
        default:
            url = serviceBase + 'web/elenaschool/myWeb';
            break;
    }
    return url;
}


