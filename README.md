# Black Jack
A classic blackjack game with Processing used for visuals

## Technologies used:
1. Java
2. Processing
3. CP5

## Process
I started with a CLI. Once that was working properly I added in Processing and CP5. Because the board and set up were so simple I didn’t feel the need for wireframes.
It was interesting, by the end I was treating my initial CLI app as an api of sorts. When Processing needed information for the display I would ‘call’ one of my ‘backend’ classes and retrieve the information. The majority of the data is stored in this back end and I have a few master variables that exchange data back and forth. One is for the player and dealer hands, another for player information, etc, etc.


## Installation Instructions:
1. Clone repo
2. Open IntelliJ (or your favorite IDE)
3. Run

## Unsolved Problems
1. Still some weird quirks...
- Aces don't properly total when more than two cards are in the hand.
- Display layout needs to be tweaked.
2. I wanted to have a mini game where the player has to, harvest moon style, farm to earn money
that will then be used to gamble with...didn't quite get to that one!

## Biggest wins and challenges
1. Processing, definitely.
2. Breaking everything out into manageable chunks
3. Saving / loading user data from a file
