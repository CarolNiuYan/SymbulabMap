Testing different population sizes:
1. Manually change vertical transmission rate in `SymSettings.cfg`  
2. Change filepath in `submission_pop_size.py` according to the instructions in the comments
3. Run `python submission_pop_size.py` on hpcc to submit tasks  

Testing different population structures:  
1. Set `WORLD_TYPE`in `SymSettings.cfg`  
2. Manually change `GRID_X` and `GRID_Y` in `SymSettings.cfg` to get the desired population size. (`GRID_X` * `GRID_Y` = population size in a grid world)
3. Change filepath in `submission_pop_struct.py` according to the instructions in the comments
4. Run `python submission_pop_struct.py` on hpcc to submit tasks