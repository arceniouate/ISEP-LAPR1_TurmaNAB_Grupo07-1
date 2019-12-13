## Executar este script gnuplot na linha de comandos
# C:\ gnuplot -e "filename='matrix.txt'"  exemploGnuplot.gp
 
# Executar este script gnuplot em Java:
#       Runtime  rt = Runtime.getRuntime(); 
# Process prcs = rt.exec("gnuplot  -e "filename='matrix.txt'"  exemploGnuplot.gp");
 
 
#Ficheiro exemploGnuplot.gp
 
reset
 
set term wxt title filename
set autoscale xfix
set autoscale yfix
set yrange [] reverse 
set cbrange[0:255]
set size square 1,1
set palette grey 
set datafile separator comma
plot filename matrix with image 
pause -1
#reread