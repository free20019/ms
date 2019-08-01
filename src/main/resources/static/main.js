Number.prototype.toFixed = function( e )  {  
    return (parseInt(this * Math.pow( 10, e  ) + 0.5)/Math.pow(10,e)).toString();  
}  
