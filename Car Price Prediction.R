# Import Packages
library(tidyverse)
library(car)
library(leaps)
library(DAAG)
library(MASS)
library(factoextra)
library(dplyr)
library(ggplot2)
library(plot3D)
# Print current directory, and put the csv in the same directory
getwd()

# Read csv file 
car_price <- read.csv("car_price_prediction.csv")
head(car_price)
colnames(car_price)

dim(car_price)
# use summary to check data types and NA
head(car_price)
summary(car_price)
# convert Levy to numeric value
car_price$Levy <- as.numeric(as.character(car_price$Levy))
## Warning: NAs introduced by coercion
# drop when airbag is 0
car_price <- subset(car_price, Airbags != 0)

# convert mileage to numeric and remove unit
car_price$Mileage <- as.numeric(gsub(" km", "", car_price$Mileage))

# remove turbo for engine volume
car_price$Engine.volume <- as.numeric(gsub(" Turbo", "", car_price$Engine.volume))

# drop unncessary columns
car_price <- subset(car_price, select = -c(Wheel, Model, Doors))

summary(car_price)
head(car_price)
# check duplicates data
summary(duplicated(car_price))

# drop duplicated rows
car_price <- car_price[!duplicated(car_price), ]

# replace NA Levy with mean
car_price$Levy[is.na(car_price$Levy)] <- ceiling(mean(car_price$Levy, na.rm=TRUE))

dim(car_price)
summary(car_price)

# drop unknown brand
table(car_price$Manufacturer)
car_price <- subset(car_price, Manufacturer != "????????????")
summary(car_price)
car_price <- subset(car_price, select = -ID)
df1 <- subset(car_price, select = -c(Drive.wheels, Color, Gear.box.type, Category, Leather.interior, Fuel.type, Manufacturer))
table(car_price$Category)
head(df1)
summary(df1)

# Regression data pre_processing
df1$year_usage<-df1$Prod..year
df1$year_usage<-2022-df1$Prod..year
df2<-subset(df1,Mileage>0 & Mileage<=200000)
df2<-subset(df2,Price>=1000)
df2<-subset(df2,select=-Airbags)
df2<-subset(df2,Levy/Price < 0.2)
qqPlot(df2$Mileage)
df2$normalize_year<-(df2$year_usage-mean(df2$year_usage)/sd(df2$year_usage))
df2$normalize_Mileage<-(df2$Mileage-mean(df2$Mileage))/sd(df2$Mileage)

# plot attributes
cor(df2,method = "pearson")
plot(x=df2$Levy,y=df2$Price)
plot(x=df2$normalize_year,y=df2$Price)
plot(x=df2$Engine.volume,y=df2$Price)
plot(x=df2$normalize_Mileage,y=df2$Price)
plot(x=df2$Cylinders,y=df2$Price)

# build regression models
model_Levy<-lm(formula=df2$Price ~ df2$Levy)
summary(model_Levy)
model_year<-lm(formula=df2$Price ~ df2$normalize_year)
summary(model_year)
model_volume<-lm(formula=df2$Price ~ df2$Engine.volume)
summary(model_volume)
model_cylinder<-lm(formula=df2$Price ~ df2$Cylinders)
summary(model_cylinder)
model_mileage<-lm(formula=df2$Price ~ df2$normalize_Mileage)
summary(model_mileage)
model<-lm(formula =df2$Price ~ df2$Levy + df2$normalize_year + df2$Engine.volume + df2$normalize_Mileage + df2$Cylinders, data=df2)
summary(model)
anova_model<-anova(model)
anova_model
ggplot(df2, aes(x = Levy, y = Price)) + 
  geom_point() +
  stat_smooth(method = "lm", col = "red")
ggplot(df2, aes(x = normalize_year, y = Price)) + 
  geom_point() +
  stat_smooth(method = "lm", col = "red")
ggplot(df2, aes(x = Engine.volume, y = Price)) + 
  geom_point() +
  stat_smooth(method = "lm", col = "red")
ggplot(df2, aes(x = Cylinders, y = Price)) + 
  geom_point() +
  stat_smooth(method = "lm", col = "red")
ggplot(df2, aes(x = normalize_Mileage, y = Price)) + 
  geom_point() +
  stat_smooth(method = "lm", col = "red")

# validation
step(lm(df2$Price ~ df2$Levy + df2$normalize_year + df2$Engine.volume + df2$normalize_Mileage + df2$Cylinders, data=df2), direction="backward")
step(lm(df2$Price ~1, data=df2),direction="forward", scope= ~df2$Levy + df2$normalize_year + df2$Engine.volume + df2$normalize_Mileage + df2$Cylinders)
step(lm(df2$Price ~1, data=df2),direction="both", scope= ~df2$Levy + df2$normalize_year + df2$Engine.volume + df2$normalize_Mileage + df2$Cylinders)
models<-regsubsets(df2$Price ~ df2$Levy + df2$normalize_year + df2$Engine.volume + df2$normalize_Mileage + df2$Cylinders, data=df2, method = "exhaustive", nvmax=5)
plot(models,scale="r2")
choices<-summary(models)
print(choices$which)
print(choices$adjr2)
print(choices$bic)
fit<-lm(Price ~ Levy + normalize_year + Engine.volume + normalize_Mileage + Cylinders, data=df2)
cvoutput<-cv.lm(data = df2, fit, m=5,  printit = FALSE)
layout(matrix(c(1,2,3,4),2,2)) # optional 4 graphs/page
plot(fit)

# Following part is for cluster analysis
# Just select first 1000 observations for analysis since taking all would cause oversize.
set.seed(35)
df3<-sample_n(df1, 1000)
head(df3)
#Remove outliers
df3<- subset(df3, select = c(Price, Mileage, Prod..year))
df3<-subset(df3,Price>=1000) # remove individuals which the price is lower than 1000$
df3<-subset(df3,Mileage>1 & Mileage<=200000) # remove individuals which Mileage is less than 1 or higher than 20000
df3$year_usage<-df3$Prod..year
df3$year_usage<-2022-df3$Prod..year
normalize <-function(x) {return((x-min(x)) / (max(x) - min(x)))} # Function for Min-max normalize data
df3$normalize_Price<-normalize(df3$Price)
df3$normalize_Mileage<-normalize(df3$Mileage)

#Calculate distance by euclidean method. Here only prince is taken into consideration
dist.euclideanClusterAnalysis<-get_dist(df3$normalize_Price, method="euclidean")
as.matrix(dist.euclideanClusterAnalysis)

#Look at the heatmap of euclidean distances
fviz_dist(dist.euclideanClusterAnalysis)
set.seed(35) # set seed to guarantee reproducibility
Cluster<-kmeans(df3$normalize_Price, centers=3,nstart=15) # Using kmean with centers of 3
print (Cluster)
plot(df3$normalize_Price, col=Cluster$cluster)

#Here take Price and Mileage into consideration.
df4 <- subset(df3, select = c(normalize_Price, normalize_Mileage))
dist.euclideanClusterAnalysis.2<-get_dist(df4, method="euclidean")
as.matrix(dist.euclideanClusterAnalysis.2)

#Look at the heatmap of euclidean distances
fviz_dist(dist.euclideanClusterAnalysis.2)
set.seed(35) # Set seed to guarantee reproducibility
Cluster.2<-kmeans(df4, centers=3,nstart=15)

# print (Cluster.2)
plot(df4, col=Cluster.2$cluster)
x<-df3$Mileage
y<-df3$year_usage
z<-df3$Price

scatter3D(x = x, y=y, z =z, color=0, theta=25, bty="g", xlab="Mileage",
          ylab="year usage", zlab = "Price", type="h", ticktype="detailed")
text3D(x,y,z,labels=rownames(df3), add= TRUE, colkey=FALSE, cex=0.5)




