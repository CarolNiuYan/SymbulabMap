To compile the program, simply go to the SymbulationEmp folder in terminal and type `make`. Then type `./symbulation` to execute the program.

### *Revisions made compared to the original version before June 2018:*  

| Original                                                                                                                                                        | Current                                                                           |
| ------------------------------------------------------------------------------------------------                                                                |:---------------------------------------------------------------------------------:|
| Both host and symbiont need 100 resources to reproduce.                                                                                                         | Host needs 1000 resources to reproduce; symbiont needs 100.                       |
| Host does not check whether its symbiont is fake or not before distributing resources with it.                                                                  | Fake symbionts do not receive or return resources.                                |
| Data nodes do not check if a symbiont is fake or not when calculating the mean interaction value for symbionts. (a fake symbiont has an interaction value of 0) | Fake symbionts' interaction values are no longer added when calculating the mean. |

