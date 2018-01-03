/**
 * @file Validations extension.
 * @author douglaspands
 * @since 2017-11-23
 */

/**
 * validate CPF
 * @param {string} strCPF  CPF Number
 * @return {boolean} "true" if the CPF is valid.
 */
function validateCPF(strCPF) {
    let Sum;
    let Remainder;
    Sum = 0;
    if (strCPF === '00000000000') return false;

    for (var i = 1; i <= 9; i++) Sum = Sum + parseInt(strCPF.substring(i - 1, i), 10) * (11 - i);
    Remainder = (Sum * 10) % 11;

    if ((Remainder === 10) || (Remainder === 11)) Remainder = 0;
    if (Remainder !== parseInt(strCPF.substring(9, 10), 10)) return false;

    Sum = 0;
    for (i = 1; i <= 10; i++) Sum = Sum + parseInt(strCPF.substring(i - 1, i), 10) * (12 - i);
    Remainder = (Sum * 10) % 11;

    if ((Remainder === 10) || (Remainder === 11)) Remainder = 0;
    if (Remainder !== parseInt(strCPF.substring(10, 11), 10)) return false;
    return true;
}
/**
 * validate CNPJ
 * @param {string} cnpj  CNPJ Number
 * @return {boolean} "true" if CNPJ is valid.
 */
function validateCNPJ(cnpj) {

    cnpj = cnpj.replace(/[^\d]+/g, '');

    if (cnpj === '') return false;

    if (cnpj.length !== 14)
        return false;

    // Remove invalid know CNPJs
    if (cnpj === '00000000000000' ||
        cnpj === '11111111111111' ||
        cnpj === '22222222222222' ||
        cnpj === '33333333333333' ||
        cnpj === '44444444444444' ||
        cnpj === '55555555555555' ||
        cnpj === '66666666666666' ||
        cnpj === '77777777777777' ||
        cnpj === '88888888888888' ||
        cnpj === '99999999999999')
        return false;

    // Validate DVs
    var size = cnpj.length - 2;
    var numbers = cnpj.substring(0, size);
    var digits = cnpj.substring(size);
    var sum = 0;
    var pos = size - 7;
    for (var i = size; i >= 1; i--) {
        sum += numbers.charAt(size - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    var result = sum % 11 < 2 ? 0 : 11 - sum % 11;
    if (result !== digits.charAt(0))
        return false;

    size = size + 1;
    numbers = cnpj.substring(0, size);
    sum = 0;
    pos = size - 7;
    for (i = size; i >= 1; i--) {
        sum += numbers.charAt(size - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    result = sum % 11 < 2 ? 0 : 11 - sum % 11;
    if (result !== digits.charAt(1))
        return false;

    return true;

}

module.exports = {
    validateCPF,
    validateCNPJ
};
