var gulp    = require('gulp');
var gutil    = require('gulp-util');
var uglify  = require('gulp-uglify');
var concat  = require('gulp-concat');
var webpack = require('webpack-stream');
var sourcemaps = require('gulp-sourcemaps');
var through = require('through2');

gulp.task('webpack', function () {
    return gulp.src('./src/main.js')
    .pipe(webpack({
        config : require('./webpack.config.js')     
      }))
    .pipe(gulp.dest('./dist'));
});

gulp.task('watch', function(){
	gulp.watch('./js/common/**/*.js', ['webpack']);
    gulp.watch('./js/page/**/*.js', ['webpack']);
    gulp.watch('./js/view/**/*.vue', ['webpack']);
})

gulp.task('build', function(){
    gulp.start('webpack'); 
});

gulp.task('default', function(){
    gulp.start('webpack'); 
});