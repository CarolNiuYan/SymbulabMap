require(ggplot2)
#import Result file
getwd()
Result <- read.csv("/Users/Aurora/Desktop/Symbulation/MAP/result/Result.csv", header = TRUE, sep = ",")

#change value to character
Result$VTR <- as.character(Result$VTR)

# boxplot(final interaction rate vs. vertical transmission rate)
ggplot(Result, aes(x = VTR, y = mean_IntVal)) + geom_boxplot(aes(color = Role), position=position_dodge(1)) +
ylim(-1,1)+
labs(title="Evolution of mutualisms across vertical transmission rates") +
theme(plot.title = element_text(color = "black", face="bold", size=12, hjust=0))+
labs(x = "Vertical Transmission Rate", y = "Final Mean Interaction Value")
