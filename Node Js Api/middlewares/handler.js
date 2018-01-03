/**
 * @file Error Hanndler model
 * @author Victor Tripeno
 * @since 2017-12-19
 */

module.exports = function (err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    const statusCode = err.status || 500;

    // render the error page
    res.status(statusCode);
    res.json(res.locals);
};
