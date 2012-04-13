Newest Functionality:

I redesigned the interactive sprites to use an InteractiveSprite interface. All interactive sprites can ge added to the
INTERACTIVE_SPRITE_GROUP and a general method primaryAction() can be called to execute the unique function of each
sprite. 

As an example, the game now contains a spring which can be moved by colliding with the object and holding 'Q'. Releasing
the key will drop the spring at its current location. Additionally, colliding the bottom of the user sprite with
the top of the spring will launch the character upwards. Because there is no gravity in my RPG game, the user character
will continually rise out of the frame. If this were implemented in the 2D platformer, it would function as a normal
jump on account of the gravity system in place.