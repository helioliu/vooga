package core;


import game.Platformer;
import game.PlatformGame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import SpriteCollisions.SpritePlatformCollision;
import sprites.*;
import sprites.Character;

import levelEditor.GameFile;
import levelEditor.SpriteInfo;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import core.VoogaGame;

public class LevelManager implements VoogaManager
{

    private static final String DEFAULT_PLAYER_GROUP_NAME = "player";

    /** A map of level number to array of [levelFilePath, levelType ] */
    private Map<Integer, String[]> myLevelOrderMap;

    /** The total number of levels */
    private int myNumOfLevels;

    /** The total number of levels completed */
    private int myNumOfLevelsCompleted = 0;

    /** The players for this game */
    private Collection<SpriteGroup<? extends Sprite>> myPlayers;

    /** The currently running game */
    private VoogaGame myGame;

    /** The current, active level for this game */
    private AbstractLevel myActiveLevel;

    /** Past playingfields that have been used in the game */
    private Collection<AbstractLevel> myPastLevels;

    /** Event manager to handle all events that occur during the game. */
    private EventManager myEventManager;


    /**
     * Sets the level map and vooga game
     * 
     * @param voogaGame whose levels this is managing
     */
    @SuppressWarnings("unchecked")
    public LevelManager (VoogaGame game)
    {
        this(game, new SpriteGroup<Sprite>(DEFAULT_PLAYER_GROUP_NAME));
    }

    
    /**
     * Sets the level map, vooga game AND players
     */
    public LevelManager(VoogaGame game, SpriteGroup<? extends Sprite>... players)
    {
        myGame = game;
        myPlayers = new ArrayList<SpriteGroup<? extends Sprite>>();
        myPlayers.addAll(Arrays.asList(players));
        myEventManager = new EventManager();
        //myEventManager.setKeyMap(game.getResourceManager().getKeyMap()); // FIXME
        myLevelOrderMap = myGame.getResourceManager().getLevelMap();
        myNumOfLevels = myLevelOrderMap.size();
        myPastLevels = new HashSet<AbstractLevel>();
    }


    /**
     * Attempts to load level with specified id. Checks to see if the level
     * being loaded is of the same type any previously played levels. If so, the
     * old level's playingfield is used so that collision managers and sprite
     * groups do not have to be re-specified in the XML file.
     * 
     * @param id representing level to load
     */
    public void loadLevel (int id)
    {
        if (!(myLevelOrderMap.containsKey(id))) throw LevelException.NON_EXISTANT_LEVEL;
        String desiredLevelPath = myLevelOrderMap.get(id)[0];
        String desiredLevelType = myLevelOrderMap.get(id)[1];

        // Cycle through all past levels and see if any of them are of the
        // requested type
        for (AbstractLevel pastLevel : myPastLevels)
        {
            String pastLevelClass = pastLevel.getClass().getName();
            pastLevelClass = pastLevelClass.substring(0, pastLevelClass.indexOf(".")); // Gets rid of ".class"
            if (pastLevelClass.equals(desiredLevelType))
            {
                myActiveLevel = pastLevel;
                myActiveLevel.clearUnusedObjects();
                myActiveLevel.parseXMLFile(desiredLevelPath, id);
                myActiveLevel.loadLevel();
                return;
            }
        }

        // If no pre-existing level of the correct type exists, create a new
        // instance
        try { 
            myActiveLevel = ((AbstractLevel) Reflection.createInstance(desiredLevelType, myPlayers, myGame));
        } catch (Exception e) {
            throw LevelException.LEVEL_CREATION_ERROR;
        }
  
        try {
            myActiveLevel.parseXMLFile(desiredLevelPath, id);
        } catch (Exception e) {
                e.printStackTrace();
            throw LevelException.LEVEL_PARSING_ERROR;
        }
        
        try {
            myActiveLevel.loadLevel();
            myPastLevels.add(myActiveLevel);
        } catch (Exception e) {
                e.printStackTrace();
            throw LevelException.LEVEL_LOADING_ERROR;
        }
    }


    /**
     * Loads the level that comes after the current level
     */
    public void loadNextLevel ()
    {
        loadLevel(myActiveLevel.getId() + 1);
    }


    /**
     * Loads the level that came before the current level
     */
    public void loadPreviousLevel ()
    {
        loadLevel(myActiveLevel.getId() - 1);
    }


    /**
     * Retrieves the highest running level
     */
    public AbstractLevel getCurrentLevel ()
    {
        if (myActiveLevel == null) return null;
        return myActiveLevel;
    }
    
    
    /**
     * Retrieves the game.
     */
    public VoogaGame getCurrentGame ()
    {
        return myGame;
    }

    
    /**
     * Returns number of levels completed
     */
    public int getNumOfLevelsCompleted ()
    {
        return myNumOfLevelsCompleted;
    }


    /**
     * Adds 1 to myNumOfLevelsCompleted
     */
    public void updateNumOfLevelsCompleted ()
    {
        myNumOfLevelsCompleted++;
    }


    /**
     * Returns the total number of levels
     */
    public int getNumOfLevels ()
    {
        return myNumOfLevels;
    }


    /**
     * Adds a random sprite from the lowest running level pool
     * 
     * @return the sprite which was just added to the playingfield
     */
    public Sprite addRandomSprite ()
    {
        if (myActiveLevel == null) return null;
        return myActiveLevel.addRandomSprite();
    }


    /**
     * Add a new sprite of the specified type to the playingfield. The sprite is
     * taken from the lowest running level sprite pool.
     * 
     * @param type of Sprite to add
     * @return the sprite which was just added to the playingfield
     */
    public Sprite addSpriteFromPool (String type)
    {
        if (myActiveLevel == null) return null;
        return myActiveLevel.addSpriteFromPool(type);
    }


    /**
     * Creates a sprite based on an archetype, and additional components
     * 
     * @param archetype of the sprite that you wish to create
     * @param x coordinate of the sprite
     * @param y coordinate of the sprite
     * @param Additional components for this sprite
     * @return the newly created sprite
     */
    public Sprite addArchetypeSprite (String type, int x, int y, IComponent ... components)
    {
        if (myActiveLevel == null) return null;
        return myActiveLevel.addArchetypeSprite(type, x, y, components);
    }


    /**
     * Changes the playingfield background to the next background in a sequence
     * of backgrounds. The background is taken from the lowest running level.
     */
    public void useNextBackground ()
    {
        if (myActiveLevel != null)
        {
            myActiveLevel.addBackground();
        }
    }


    /**
     * Plays the next music file from a sequence of music files. The music is
     * taken from the lowest running level.
     */
    public void useNextMusic ()
    {
        if (myActiveLevel != null)
        {
            myActiveLevel.addMusic();
        }
    }


    /**
     * Adds a player to the player group Change will immediately be reflected on
     * the playingfield
     * 
     * @param player sprite group to add
     */
        public void addPlayer (SpriteGroup<? extends Sprite> player)
    {
        if(player == null) return;
        myPlayers.add(player);
    }


    /**
     * Sets the level order map
     * 
     * @param the new level order mapping level number to the associated XML
     *            file path
     */
    public void setLevelOrder (Map<Integer, String[]> levelOrder)
    {
        myLevelOrderMap = levelOrder;
    }
    
    
    /**
     * Provides the event manager for game state.
     */
    @Override
    public EventManager getEventManager ()
    {
        return myEventManager;
    }
    
    
    /**
     * Add a custom XML tag to the parser. Used when adding custom tags to 
     * a level XML file.
     * @param tag Custom XML tag
     */
    public void addParserDefinition (XMLTag tag)
    {
        myActiveLevel.addParserDefinition(tag);
    }


    /**
     * Updates the level (which is a playingfield)
     * 
     * @param elapsedTime
     */
    public void update (long elapsedTime)
    {
        if (myActiveLevel != null)
        {
            myActiveLevel.update(elapsedTime);
        }
    }


    /**
     * Renders the level (which is a playingfield)
     * 
     * @param The graphics which will be used to render
     */
    public void render (Graphics2D g)
    {
        if (myActiveLevel != null)
        {
            myActiveLevel.render(g);
        }
    }
}

		


