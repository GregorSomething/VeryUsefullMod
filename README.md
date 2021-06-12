# VeryUsefullMod
This mod is very useful :)
## Features
What useful stuff it does?
### Filter
Filer filters client side minecraft log, by removing all entries with blocked phrases. \
Mod config allows to set those. And it can be turned off completely if wanted.
### Chat commands
**?calc** Can be used as in game calculator, it is powerful, if things are entered correctly. \
**?time** Outputs time and date (DD/MM/YYYY  HH:MM:SS) \
**?chat** Outputs table of text art, that can be easily copied from chat with clickEvent \
thos must be configured in config exsample for one `&6Shrug: ##&e¯\\_(ツ)_/¯##&e¯\\_(ツ)_/¯##¯\\_(ツ)_/¯"` \
schema: `Name: ## inChatText ## hoverText ## copyText` \
**?morse \<recipient\>** Sends message in morse code to recipient via /msg \
if message is too large it will be sent in parts. Regex to identify and remove /msg part is configured in config. \
Also all messages via /msg are translated form morse when msg regex is correct.

**NB! All messages starting with "?" are canceled so server doesn't get them. And all recived messages are client side only.**
### Screenshot name and dir change
Screenshots name and save dir in minecraft/screenshots folder can be changed in config. \
dir can be changed by adding its name(s) before screenshots name.

Option | Value
------ | -------------
%y   | Short year (eg 21)
%Y  | Long year (eg 2021)
%mo/%MO   | Month (eg 6/06)
%d/%D   | Day of month 
%h/%H   | Hour 
%m/%M   | Minute 
%s/%S   | Second 
%gm/%GM | Client's gamemode (lower/upper)
%posx | Camera's X coordinate (rounded)
%posy | Camera's Y coordinate (rounded)
%posz | Camera's Z coordinate (rounded)
%yaw | Camera's yaw
%pitch | Camera's pitch
%gver | Game version (not useful)
%sver | Map version (some number)

Exsample `%Y.%MO/%H:%M:%S` will make screenshot ./minecraft/screenshots/2021.06/16:16:20.png