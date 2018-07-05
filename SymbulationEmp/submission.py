from tempfile import mkstemp
from shutil import move
from os import fdopen, remove
import os

script_name = "/mnt/home/f0005303/test_pop_size/SymbulationEmp/symbulation"

treatments = ["${PBS_ARRAYID} 0.1","${PBS_ARRAYID} 0.2","${PBS_ARRAYID} 0.3","${PBS_ARRAYID} 0.4","${PBS_ARRAYID} 0.5","${PBS_ARRAYID} 0.6","${PBS_ARRAYID} 0.7","${PBS_ARRAYID} 0.8","${PBS_ARRAYID} 0.9","${PBS_ARRAYID} 1.0"]

header = "#!/bin/bash -login\n\n#PBS -l walltime=00:15:00\n#PBS -l nodes=1:ppn=1\n#PBS -l mem=2gb\n#PBS -M qishuyi@grinnell.edu\n#PBS -m abe\n#PBS -N burst_size_tests\n#PBS -t 1-20\n\ncd /mnt/home/f0005303/test_burst_size/SymbulationEmp/initialMOI=0.01\nmodule load GNU/5.2\nmodule load Java/jdk1.8.0\n"

def replace(file_path, seed, VTR):
	fh, abs_path = mkstemp()
    	with fdopen(fh,'w') as new_file:
		with open(file_path) as old_file:
			for line in old_file:
				if ("SEED" in line):
		   			line = "set SEED "+str(seed)
				if ("VERTICAL_TRANSMISSION " in line):
					line = "set VERTICAL_TRANSMISSION "+str(VTR)
                new_file.write(line)
	remove(file_path)
	move(abs_path, file_path)

for t in range(len(treatments)):
    treatment = treatments[t]
    params = treatment.split()
    seed = params[0]
    VTR  = params[1]
    replace("/mnt/home/f0005303/test_burst_size/SymbulationEmp/SymSettings.cfg", seed, VTR)
    tempfilename = 'temp_'+str(t)+'.qsub'
    tempfile = open(tempfilename, 'w')
    tempfile.write(header)
    tempfile.write(script_name)
    tempfile.write(" ")
    tempfile.write(treatments[t])
    tempfile.close()
    os.system("qsub {0}".format(tempfilename))
    print "submitted", t
    os.system("rm {0}".format(tempfilename))


