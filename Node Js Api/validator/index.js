/**
 * @file Module to validate Fields
 * @author Victor Tripeno
 * @author douglaspands
 * @since 2017-11-23
 */

const _ = require('lodash');
const moment = require('moment');
const constant = require('./constants');
const ext = require('./extends');
const { ObjectID } = require('mongodb');

/**
 * Module to verify objects.
 * @param {object} object Object which will be verified.
 * @return {object} Object with the validation.
 */
function inspection(object) {

    const errorList = [];

    /**
     * @typedef {object} InspectFail
     * @property {string} field
     * @property {any} value
     * @property {string} message
     */
    function InspectFail(field, value, errorMessage) {
        this.field = field;
        this.value = value;
        this.message = errorMessage;
    }

    /**
     * Function which receive a field and an error message
     * @param {string} property Property validated name.
     * @param {string} errorMessage Message which will be showed.
     */
    function CheckField(property, errorMessage) {

        if (!(this instanceof CheckField)) {
            return new CheckField(property, errorMessage);
        }

        let flgError = false;
        let flgValidation = true;
        let element = _.get(object, property);

        /**
         *  Function to transform the optional validation if the field is empty.
         * @return {void}
         */
        this.isOptional = () => {

            if (!element) flgValidation = false;
            return this;

        };

        /**
         * Function to verify if date is valid
         * @return {void}
         */
        this.isDateValid = () => {

            if (!flgValidation) return this;

            let ret = moment(element).isValid();
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if MongoDB ID is valid.
         * @return {void}
         */
        this.isMongoId = () => {

            if (!flgValidation) return this;

            if (!ObjectID.isValid(element) && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the field is an email
         * @return {void}
         */
        this.isEmail = () => {

            if (!flgValidation) return this;

            const regex = new RegExp(constant.REGEX.EMAIL, 'g');
            const ret = regex.test(element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the field is a phone number
         * @return {void}
         */
        this.isPhoneNumber = () => {

            if (!flgValidation) return this;

            const regex = new RegExp(constant.REGEX.PHONE, 'g');
            const ret = regex.test(element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the field is CEP
         * @return {void}
         */
        this.isCEP = () => {

            if (!flgValidation) return this;

            const regex = new RegExp(constant.REGEX.CEP, 'g');
            const ret = regex.test(element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the field is a boolean
         * @return {void}
         */
        this.isBoolean = () => {

            if (!flgValidation) return this;

            let ret = _.isBoolean(element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the field is a number.
         * @return {void}
         */
        this.isNumber = () => {

            if (!flgValidation) return this;

            let ret = _.isNumber(element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the field is a text.
         * @return {void}
         */
        this.isString = () => {

            if (!flgValidation) return this;

            let ret = _.isString(element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify the field size
         * @param {number} min Minimun size allowed
         * @param {number} max Maximum size allowed
         * @return {void}
         */
        this.isLength = (min, max) => {

            if (!flgValidation) return this;

            let _min = (_.isNumber(min)) ? min : 0;
            let _max = (_.isNumber(max)) ? max : _min;

            let ret = (_.size(element) >= _min && _.size(element) <= _max);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the text is inside a list.
         * @param {array} list
         * @return {void}
         */
        this.isIn = (list) => {

            if (!flgValidation) return this;

            let _list = _.isArray(list) ? list : [];
            let ret = _.includes(_list, element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the texto is not empty.
         * @return {void}
         */
        this.notEmpty = (list) => {

            if (!flgValidation) return this;

            let ret = !_.isEmpty(element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the CPF is valid.
         * @return {void}
         */
        this.isCPF = () => {

            if (!flgValidation) return this;

            let ret = ext.validateCPF(element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the CNPJ is valid.
         * @return {void}
         */
        this.isCNPJ = () => {

            if (!flgValidation) return this;

            let ret = ext.validateCNPJ(element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Function to verify if the brazilian UF is valid.
         * @return {void}
         */
        this.isUF = () => {

            if (!flgValidation) return this;

            let _element = _.isString(element) ? element.toUpperCase() : '';
            let ret = _.includes(constant.UF_LIST, _element);
            if (!ret && !flgError) {
                errorList.push(new InspectFail(property, element, errorMessage));
                flgError = true;
            }
            return this;

        };

        /**
         * Custom Function to validation.
         * @param {function} callback Custom function to validation, it returns true or false
         * @return {void}
         */
        this.custom = (callback) => {

            if (!flgValidation) return this;

            if (typeof callback === 'function') {
                let ret = callback(element);
                if (!ret && !flgError) {
                    errorList.push(new InspectFail(property, element, errorMessage));
                    flgError = true;
                }
            }
            return this;
        };

    };

    /**
     * Return Error list
     * @return {array} error list.
     */
    function getListErrors() {
        return errorList;
    }

    /**
     * Return the error object.
     * @return {object} error object.
     */
    function checkReportForREST() {
        if (errorList.length > 0) {
            return {
                code: 'validation error',
                message: errorList
            };
        } else {
            return null;
        }
    }


    /**
     * Returned functions.
     */
    return {
        checkField: CheckField,
        getListErrors,
        checkReportForREST
    };
}

module.exports = inspection;
