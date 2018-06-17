// This is the main function for the NATIVE version of this project.

#include <iostream>
#include "../SymWorld.h"
#include "source/config/ArgManager.h"

using namespace std;

EMP_BUILD_CONFIG( SymConfigBase,
                 VALUE(SEED, double, 10, "What value should the random seed be?"),
                 VALUE(MUTATION_RATE, double, 0.002, "Standard deviation of the distribution to mutate by"),
                 VALUE(SYNERGY, double, 5, "Amount symbiont's returned resources should be multiplied by"),
                 VALUE(VERTICAL_TRANSMISSION, double, 1, "Value 0 to 1 of probability of symbiont vertically transmitting when host reproduces"),
		  VALUE(HOST_INT, double, 0, "Interaction value from -1 to 1 that hosts should have initially, -2 for random"),
		  VALUE(SYM_INT, double, 0, "Interaction value from -1 to 1 that symbionts should have initially, -2 for random"),
                  VALUE(WORLD_TYPE, int, 1, "Type of the world (1 for Mixed and 2 for Grid"),
                 VALUE(GRID_X, int, 5, "Width of the world"),
                 VALUE(GRID_Y, int, 5, "Height of world"),
                 VALUE(UPDATES, int, 1, "Number of updates to run before quitting"),

                 )
//TODO: add option for random host and sym int values


	
int main(int argc, char * argv[])
{    
    SymConfigBase config;
    
    config.Read("SymSettings.cfg");

    auto args = emp::cl::ArgManager(argc, argv);
    if (args.ProcessConfigOptions(config, std::cout, "SymSettings.cfg") == false) {
	cout << "There was a problem in processing the options file." << endl;
	exit(0);
      }
    if (args.TestUnknown() == false) exit(0); //Leftover args no good

    double numupdates = config.UPDATES();
    double POP_SIZE = 400;
    bool random_phen_host = false;
    bool random_phen_sym = false;
    if(config.HOST_INT() == -2) random_phen_host = true;
    if(config.SYM_INT() == -2) random_phen_sym = true;

    emp::Random random(config.SEED());
        
    SymWorld world(random);
    world.SetPopStruct_Mixed();
    world.SetVertTrans(config.VERTICAL_TRANSMISSION());
    world.SetMutRate(config.MUTATION_RATE());
    //Set up files
    world.SetupPopulationFile().SetTimingRepeat(10);
    world.SetupHostIntValFile("HostVals"+to_string(config.SEED())+"_"+to_string(config.VERTICAL_TRANSMISSION())+".data").SetTimingRepeat(10);
    world.SetupSymIntValFile("SymVals"+to_string(config.SEED())+"_"+to_string(config.VERTICAL_TRANSMISSION())+".data").SetTimingRepeat(10);

    //inject organisms
    for (size_t i = 0; i < POP_SIZE; i++){
      Symbiont new_sym; 
      Host *new_org;
      if(random_phen_sym) new_sym = *(new Symbiont(random.GetDouble(0, 1)));
      else new_sym = *(new Symbiont(config.SYM_INT()));
      if (random_phen_host) new_org = new Host(random.GetDouble(-1, 1), new_sym);
      else new_org = new Host(config.HOST_INT(), new_sym);

      // Host *new_org = new Host(config.HOST_INT(), *(new Symbiont(config.SYM_INT())));
      world.Inject(*new_org);
    }

    //Loop through updates
      
    for (int i = 0; i < numupdates; i++) {
      cout << i << endl;
      world.Update();
    }

}
