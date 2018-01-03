/**
 * @file Not Found - 404 model
 * @author Victor Tripeno
 * @since 2017-12-19
 */
module.exports = function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
};
