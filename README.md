# orderApp
# Food Ordering System with AI Feature

## Overview
This project is a Java console application for food ordering. The user can choose a food item, add extras, select a delivery method, and optionally receive AI-generated nutrition analysis.
The system uses Azure OpenAI to return estimated calories, protein, carbs, fat, a better option, and a health tip.

## Requirements
- Java JDK 15 or higher
- Azure OpenAI API key

## 1. Install Dependencies
No additional dependencies need to be installed.
Ensure Java JDK 15 or higher is installed.

Check Java version:
java -version

## 2. Set API Key (given by professor)
Mac:
export AZURE_API_KEY=*******

Windows:
set AZURE_API_KEY=*********

## 3. Run the Application
Run the java program by clicking run.

## How It Works
1. The menu is displayed with food prices.
2. The user selects a food item.
3. The user can add cheese and sauce.
4. The user selects delivery method (home or pickup).
5. The user can choose to get AI nutrition analysis.
6. The system displays the order summary and notifications.

## App Pricing
- Burger: 20
- Pizza: 30
- Pasta: 45
- Extra cheese: +5
- Extra sauce: +3

## Notes
- The application uses Azure OpenAI Chat Completions API
- The AI feature only works if the API key is set correctly

## Authors
Salma Abouzeid, Mawada Ghalab, Aly Abouzeid
