#include "source/Evolve/World.h"
#include "source/tools/Random.h"
#include <set>
#include "SymOrg.h"
#include "source/tools/random_utils.h"
#include "source/data/DataFile.h"

class SymWorld : public emp::World<Host>{
private:
    double vertTrans = 0;
    double mut_rate = 0;
    emp::Random random;
    
    emp::Ptr<emp::DataMonitor<double, emp::data::Histogram>> data_node_hostintval;
    emp::Ptr<emp::DataMonitor<double, emp::data::Histogram>> data_node_symintval;
    
public:
    //set fun_print_org to equal function that prints hosts/syms correctly
    SymWorld(emp::Random &random) : emp::World<Host>(random) {
        fun_print_org = [](Host & org, std::ostream & os) {
            os << PrintHost(&org);
        };
    }
    
    void SetVertTrans(double vt) {
        vertTrans = vt;
    }
    void SetMutRate(double mut) {
        mut_rate = mut;
    }
    
    
    bool WillTransmit() {
        if (random.GetDouble(0.0, 1.0) <= vertTrans) {
            return true;
        }  else {
            return false;
        }
        
        
    }
    
    emp::DataFile & SetupSymIntValFile(const std::string & filename) {
        auto & file = SetupFile(filename);
        auto & node = GetSymIntValDataNode();
        node.SetupBins(-1.0, 1.0, 20);
        file.AddVar(update, "update", "Update");
        file.AddMean(node, "mean_intval", "Average symbiont interaction value");
        file.AddHistBin(node, 0, "Hist_-1", "Count for histogram bin -1 to <-0.9");
        file.AddHistBin(node, 1, "Hist_-0.9", "Count for histogram bin -0.9 to <-0.8");
        file.AddHistBin(node, 2, "Hist_-0.8", "Count for histogram bin -0.8 to <-0.7");
        file.AddHistBin(node, 3, "Hist_-0.7", "Count for histogram bin -0.7 to <-0.6");
        file.AddHistBin(node, 4, "Hist_-0.6", "Count for histogram bin -0.6 to <-0.5");
        file.AddHistBin(node, 5, "Hist_-0.5", "Count for histogram bin -0.5 to <-0.4");
        file.AddHistBin(node, 6, "Hist_-0.4", "Count for histogram bin -0.4 to <-0.3");
        file.AddHistBin(node, 7, "Hist_-0.3", "Count for histogram bin -0.3 to <-0.2");
        file.AddHistBin(node, 8, "Hist_-0.2", "Count for histogram bin -0.2 to <-0.1");
        file.AddHistBin(node, 9, "Hist_-0.1", "Count for histogram bin -0.1 to <0.0");
        file.AddHistBin(node, 10, "Hist_0.0", "Count for histogram bin 0.0 to <0.1");
        file.AddHistBin(node, 11, "Hist_0.1", "Count for histogram bin 0.1 to <0.2");
        file.AddHistBin(node, 12, "Hist_0.2", "Count for histogram bin 0.2 to <0.3");
        file.AddHistBin(node, 13, "Hist_0.3", "Count for histogram bin 0.3 to <0.4");
        file.AddHistBin(node, 14, "Hist_0.4", "Count for histogram bin 0.4 to <0.5");
        file.AddHistBin(node, 15, "Hist_0.5", "Count for histogram bin 0.5 to <0.6");
        file.AddHistBin(node, 16, "Hist_0.6", "Count for histogram bin 0.6 to <0.7");
        file.AddHistBin(node, 17, "Hist_0.7", "Count for histogram bin 0.7 to <0.8");
        file.AddHistBin(node, 18, "Hist_0.8", "Count for histogram bin 0.8 to <0.9");
        file.AddHistBin(node, 19, "Hist_0.9", "Count for histogram bin 0.9 to 1.0");
        
        
        file.PrintHeaderKeys();
        
        return file;
    }
    emp::DataFile & SetupHostIntValFile(const std::string & filename) {
        auto & file = SetupFile(filename);
        auto & node = GetHostIntValDataNode();
        node.SetupBins(-1.0, 1.0, 20);
        
        file.AddVar(update, "update", "Update");
        file.AddMean(node, "mean_intval", "Average host interaction value");
        file.AddHistBin(node, 0, "Hist_-1", "Count for histogram bin -1 to <-0.9");
        file.AddHistBin(node, 1, "Hist_-0.9", "Count for histogram bin -0.9 to <-0.8");
        file.AddHistBin(node, 2, "Hist_-0.8", "Count for histogram bin -0.8 to <-0.7");
        file.AddHistBin(node, 3, "Hist_-0.7", "Count for histogram bin -0.7 to <-0.6");
        file.AddHistBin(node, 4, "Hist_-0.6", "Count for histogram bin -0.6 to <-0.5");
        file.AddHistBin(node, 5, "Hist_-0.5", "Count for histogram bin -0.5 to <-0.4");
        file.AddHistBin(node, 6, "Hist_-0.4", "Count for histogram bin -0.4 to <-0.3");
        file.AddHistBin(node, 7, "Hist_-0.3", "Count for histogram bin -0.3 to <-0.2");
        file.AddHistBin(node, 8, "Hist_-0.2", "Count for histogram bin -0.2 to <-0.1");
        file.AddHistBin(node, 9, "Hist_-0.1", "Count for histogram bin -0.1 to <0.0");
        file.AddHistBin(node, 10, "Hist_0.0", "Count for histogram bin 0.0 to <0.1");
        file.AddHistBin(node, 11, "Hist_0.1", "Count for histogram bin 0.1 to <0.2");
        file.AddHistBin(node, 12, "Hist_0.2", "Count for histogram bin 0.2 to <0.3");
        file.AddHistBin(node, 13, "Hist_0.3", "Count for histogram bin 0.3 to <0.4");
        file.AddHistBin(node, 14, "Hist_0.4", "Count for histogram bin 0.4 to <0.5");
        file.AddHistBin(node, 15, "Hist_0.5", "Count for histogram bin 0.5 to <0.6");
        file.AddHistBin(node, 16, "Hist_0.6", "Count for histogram bin 0.6 to <0.7");
        file.AddHistBin(node, 17, "Hist_0.7", "Count for histogram bin 0.7 to <0.8");
        file.AddHistBin(node, 18, "Hist_0.8", "Count for histogram bin 0.8 to <0.9");
        file.AddHistBin(node, 19, "Hist_0.9", "Count for histogram bin 0.9 to 1.0");
        
        
        file.PrintHeaderKeys();
        
        return file;
    }
    
    double CalcIntVal(size_t i) {
        return pop[i]->GetIntVal();
    }
    
    
    //need clarification
    double CalcSymIntVal(size_t i) {
        double totalinterval = 0;
        //TODO: this should probably be a reference to Symbiont
        for(int j = 0; j < pop[i]->NumSym(); j++){
            totalinterval += pop[i]->GetSymbiont()[j].GetIntVal();
        }
        return totalinterval / pop[i]->NumSym();
    }
    
    emp::DataMonitor<double, emp::data::Histogram>& GetHostIntValDataNode() {
        if (!data_node_hostintval) {
            data_node_hostintval.New();
            OnUpdate(
                     [this](size_t){
                         data_node_hostintval->Reset();
                         for (size_t i = 0; i< pop.size(); i++) {
                             if (IsOccupied(i)) data_node_hostintval->AddDatum(CalcIntVal(i));
                         }
                     }
                     );
        }
        return *data_node_hostintval;
    }
    
    
    
    emp::DataMonitor<double, emp::data::Histogram>& GetSymIntValDataNode() {
        if (!data_node_symintval) {
            data_node_symintval.New();
            OnUpdate(
                     [this](size_t){
                         data_node_symintval->Reset();
                         for (size_t i = 0; i< pop.size(); i++) {
                             if (IsOccupied(i)) data_node_symintval->AddDatum(CalcSymIntVal(i));
                         }
                     }
                     );
        }
        return *data_node_symintval;
    }
    
    void Update(size_t new_resources, int birth_rate, int infection_rate) {
        emp::World<Host>::Update();
        
        //TODO: put in fancy scheduler at some point
        
        emp::vector<size_t> schedule = emp::GetPermutation(random, GetSize());
        
        // divvy up and distribute resources to host and symbiont in each cell
        for (size_t i : schedule) {

            if (IsOccupied(i) == false) continue;  // no organism at that cell
            
            //Would like to shove reproduction into Process, but it gets sticky with Symbiont reproduction
            //Could put repro in Host process and population calls Symbiont process and places offspring as necessary?
            pop[i]->Process(random);
            //Check reproduction
            if (pop[i]->GetPoints() >= 100 ) {  // host replication
                // will replicate & mutate a random offset from parent values
                // while resetting resource points for host and symbiont to zero
                std::vector<Symbiont> host_symbionts = pop[i]->GetSymbiont();
                int num_sym = pop[i]->NumSym();
                // Vertical transmission for each of its symbionts

                //randomly choose symbionts to reproduce
                //just one kid for each symbiont
                for (int j = 0; j < num_sym; j++) {
                  if (WillTransmit()) { //Vertican transmission!
                    std::vector<Symbiont> shuffled = pop[i]->GetSymbiont();
                    std::random_shuffle (shuffled.begin(), shuffled.end());                   
                    Symbiont offspring(shuffled[j].GetIntVal(), 0.0);
                    offspring.mutate(random, mut_rate);
                    if(pop[i]->NumSym() < infection_rate){
                      pop[i]->AddSym(offspring); //constructor that takes parent values
                    }//vertical transmission new born symbionts capped                         
                    pop[i]->GetSymbiont()[j].mutate(random, mut_rate); //mutate parent symbiont
                  }
                }
                Host *host_baby = new Host(pop[i]->GetIntVal(),pop[i]->GetSymbiont(),std::set<int>(), 0.0,infection_rate);
                host_baby->mutate(random, mut_rate);
                pop[i]->mutate(random, mut_rate); //parent mutates and loses current resources, ie new organism but same symbiont
                pop[i]->SetPoints(0);
                //TODO: is this how I did it for the dissertation? reset parent completely?
                DoBirth(*host_baby, i); //Automatically deals with grid
            }
            // Symbiont's horizontal transmission
            std::vector<Symbiont> host_syms = pop[i]->GetSymbiont();
            int num_sym = pop[i]->NumSym();
            for (int j = 0; j < num_sym; j++) {
                if (host_syms[j].GetPoints() >= 100) {
                    //TODO: check symbiont reproduction value
                    // symbiont reproduces independently (horizontal transmission) if it has >= 100 resources
                    // new symbiont in this host with mutated value
                    // TODO: Make SymDoBirth instead of injecting
                    pop[i]->ResetSymPoints(j);
                    std::vector<Symbiont> sym_baby = {};
                    for(int k = 0; k < birth_rate; k++) {
                        Symbiont offspring(pop[i]->GetSymbiont()[j]);
                        offspring.mutate(random, mut_rate);
                        sym_baby.push_back(offspring);
                    }
                    
                    pop[i]->GetSymbiont()[j].mutate(random, mut_rate);
                    
                    // pick new host to infect, if the host has fewer than infection_rate symbionts
                    for (int k = 0; k < birth_rate; k++) {
                        int newLoc = GetRandomCellID();
                        if (IsOccupied(newLoc) == true) {
                            if(pop[newLoc]->NumSym() < infection_rate){
                                pop[newLoc]->AddSym(sym_baby[k]);
                            }
                        }
                    }
                }
            }
        }
    }
};


