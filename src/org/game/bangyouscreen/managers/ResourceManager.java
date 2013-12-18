package org.game.bangyouscreen.managers;

import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.adt.color.Color;
import org.game.bangyouscreen.BangYouScreenActivity;
import org.game.bangyouscreen.util.DataConstant;

import android.content.Context;
import android.graphics.Typeface;

/**
 * 游戏资源管理 
 * @author zuowhat 2013-11-25
 * @version 1.0
 */
public class ResourceManager extends Object{
	
	private static final ResourceManager INSTANCE = new ResourceManager();
	public FixedStepEngine engine;
	public Context context;
	public BangYouScreenActivity activity;
	public float cameraWidth;
	public float cameraHeight;
	public float cameraScaleFactorX;
	public float cameraScaleFactorY;
	private String mPreviousAssetBasePath = ""; //这个变量将被用来恢复TextureFactory的默认路径
	private TextureOptions mNormalTextureOption = TextureOptions.BILINEAR;
	
	public static TextureRegion mainMenuBackgroundTR;
	public static TiledTextureRegion mainMenuButtons;
	public static TextureRegion mainMenuTitleTR;
	public static Font mFont;
	public static Font sysFont;
	public static TiledTextureRegion numberTTR;
	public static TextureRegion menuClouds1;
	public static TextureRegion menuClouds2;
	public static TiledTextureRegion greenButtonTTR;
	public static TiledTextureRegion redButtonTTR;
	public static TextureRegion shareGameBG;
	public static TextureRegion xue1;
	public static TextureRegion xue2;
	public static TextureRegion theme1Temp;
	public static TextureRegion theme2Temp;
	public static TextureRegion themeBG;
	public static TextureRegion loadingBG;
	public static TextureRegion loadingBG1;
	public static TiledTextureRegion loadingBG2;
	public static TiledTextureRegion loadingFont;
	public static TiledTextureRegion[] mxdBoss_TTRArray = new TiledTextureRegion[DataConstant.THEME_1_BOSS_NUM];
	public static TextureRegion[] mxdBoss_InfoTRArray = new TextureRegion[DataConstant.THEME_1_BOSS_NUM];
	public static TextureRegion homeTR;
	public static TextureRegion backTR;
	public static TiledTextureRegion gamePauseBG;
	public static TiledTextureRegion gamePauseMenu;
	public static TiledTextureRegion weaponTTR;
	public static TiledTextureRegion magicTTR;
	public static TiledTextureRegion[] magicASTTRArray = new TiledTextureRegion[DataConstant.MAGIC_NUM];
	public static TiledTextureRegion propsTTR;
	public static TiledTextureRegion bossBlackBG;
	public static TiledTextureRegion layerTitle;
	public static TextureRegion gameGold;
	public static TiledTextureRegion bigBang;
	public static TiledTextureRegion wuya;
	public static TiledTextureRegion clockTime;
	public static TiledTextureRegion iconCooling;
	public static TextureRegion shopBG;
	public static TextureRegion shopMenuBG;
	public static TiledTextureRegion shopMenu;
	public static TextureRegion shopInfoBG;
	public static TiledTextureRegion shopInfoRowsBG;
	public static TiledTextureRegion shopPropBG;
	
	public ResourceManager(){
	}

	public static ResourceManager getInstance(){
		return INSTANCE;
	}
	
	public static Context getContext(){
		return getInstance().context;
	}
	
	public static FixedStepEngine getEngine(){
		return getInstance().engine;
	}
	
	public static BangYouScreenActivity getActivity(){
		return getInstance().activity;
	}
	
	public static SmoothCamera getCamera(){
		return (SmoothCamera) getInstance().engine.getCamera();
	}
	
	/**
	 * 用于复位镜头
	 * @author zuowhat 2013-11-25
	 * @since 1.0
	 */
	public static void setupForMenus() {
		final SmoothCamera sc = ResourceManager.getCamera();
		sc.setBoundsEnabled(false);
		sc.setChaseEntity(null);
		sc.setZoomFactorDirect(1f);
		sc.setZoomFactor(1f);
		sc.setCenterDirect(ResourceManager.getInstance().cameraWidth / 2f,
				ResourceManager.getInstance().cameraHeight / 2f);
		sc.setCenter(ResourceManager.getInstance().cameraWidth / 2f,
				ResourceManager.getInstance().cameraHeight / 2f);
		sc.clearUpdateHandlers();
	}

	public static void setup(BangYouScreenActivity pActivity, FixedStepEngine pEngine, Context pContext, float pCameraWidth, float pCameraHeight, float pCameraScaleX, float pCameraScaleY){
		getInstance().activity = pActivity;
		getInstance().engine = pEngine;
		getInstance().context = pContext;
		getInstance().cameraWidth = pCameraWidth;
		getInstance().cameraHeight = pCameraHeight;
		getInstance().cameraScaleFactorX = pCameraScaleX;
		getInstance().cameraScaleFactorY = pCameraScaleY;
	}
	
	private TextureRegion getLimitableTR(final String pTextureRegionPath,
			final TextureOptions pTextureOptions) {
		final IBitmapTextureAtlasSource bitmapTextureAtlasSource = AssetBitmapTextureAtlasSource
				.create(this.activity.getAssets(),BitmapTextureAtlasTextureRegionFactory.getAssetBasePath() + pTextureRegionPath);
		final BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(
				this.activity.getTextureManager(),
				bitmapTextureAtlasSource.getTextureWidth(),
				bitmapTextureAtlasSource.getTextureHeight(), pTextureOptions);
		final TextureRegion textureRegion = new TextureRegion(
				bitmapTextureAtlas, 0, 0,
				bitmapTextureAtlasSource.getTextureWidth(),
				bitmapTextureAtlasSource.getTextureHeight(), false);
		bitmapTextureAtlas.addTextureAtlasSource(bitmapTextureAtlasSource, 0, 0);
		bitmapTextureAtlas.load();
		return textureRegion;
	}
	
	private TiledTextureRegion getLimitableTTR(String pTiledTextureRegionPath, int pColumns, int pRows, TextureOptions pTextureOptions) {
		final IBitmapTextureAtlasSource bitmapTextureAtlasSource = AssetBitmapTextureAtlasSource.create(activity.getAssets(), BitmapTextureAtlasTextureRegionFactory.getAssetBasePath() + pTiledTextureRegionPath);
		final BitmapTextureAtlas bitmapTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), bitmapTextureAtlasSource.getTextureWidth(), bitmapTextureAtlasSource.getTextureHeight(), pTextureOptions);
		final ITextureRegion[] textureRegions = new ITextureRegion[pColumns * pRows];

		final int tileWidth = bitmapTextureAtlas.getWidth() / pColumns;
		final int tileHeight = bitmapTextureAtlas.getHeight() / pRows;

		for(int tileColumn = 0; tileColumn < pColumns; tileColumn++) {
			for(int tileRow = 0; tileRow < pRows; tileRow++) {
				final int tileIndex = tileRow * pColumns + tileColumn;
				final int x = tileColumn * tileWidth;
				final int y = tileRow * tileHeight;
				textureRegions[tileIndex] = new TextureRegion(bitmapTextureAtlas, x, y, tileWidth, tileHeight, false);
			}
		}

		final TiledTextureRegion tiledTextureRegion = new TiledTextureRegion(bitmapTextureAtlas, false, textureRegions);
		bitmapTextureAtlas.addTextureAtlasSource(bitmapTextureAtlasSource, 0, 0);
		bitmapTextureAtlas.load();
		return tiledTextureRegion;
	}
	
	public static void loadLoadingResources(){
		getInstance().loadLoadingTextures();
	}
	
	public static void loadMenuResources(){
		getInstance().loadMenuTextures();
		getInstance().loadSharedResources();
	}
	
	public static void loadThemeResources(){
		getInstance().loadThemeTextures();
		getInstance().loadSharedResources();
	}
	
	public static void loadGameResources(){
		getInstance().loadGameTextures();
		getInstance().loadSharedResources();
	}
	
	public static void loadShopResources(){
		getInstance().loadShopTextures();
		getInstance().loadSharedResources();
	}
	
	/**
	 * 完成关卡后，加载下一个BOSS资源 
	 * @author zuowhat 2013-11-25
	 * @since 1.0
	 */
	public static void loadBossResources(){
		getInstance().loadThemeBoss();
	}
	
	private void loadSharedResources(){
		getInstance().loadSharedTextures();
		getInstance().loadFonts();
	}
	
	// ============================ 加载画面纹理  ================= //
	private void loadLoadingTextures(){
		mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/loading/");
		if(loadingBG == null){
			loadingBG = getLimitableTR("loadingBG.png",mNormalTextureOption);
		}
		if(loadingBG2 == null){
			loadingBG2 = getLimitableTTR("loadingBG2.png",2,1,mNormalTextureOption);
		}
		if(loadingFont == null){
			loadingFont = getLimitableTTR("loadingFont.png",1,3,mNormalTextureOption);
		}
		
		
	}
	
	
	
	// ============================ 菜单纹理  ================= //
	private void loadMenuTextures(){
		mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		if(mainMenuBackgroundTR == null){
			mainMenuBackgroundTR = getLimitableTR("mainBG.png",mNormalTextureOption);
		}
		if(mainMenuTitleTR == null){
			mainMenuTitleTR = getLimitableTR("mainTitle.png",mNormalTextureOption);
		}
		if(mainMenuButtons == null){
			mainMenuButtons = getLimitableTTR("mainMenu.png",1,3,mNormalTextureOption);
		}
		if(menuClouds1 == null){
			menuClouds1 = getLimitableTR("menuClouds1.png",mNormalTextureOption);
		}
		if(menuClouds2 == null){
			menuClouds2 = getLimitableTR("menuClouds2.png",mNormalTextureOption);
		}

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
	}
	
	// ============================ 装备库纹理  ================= //
	private void loadShopTextures(){
		mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shop/");
		if(shopMenu == null){
			shopMenu = getLimitableTTR("shopMenu.png",2,3,mNormalTextureOption);
		}
		if(shopMenuBG == null){
			shopMenuBG = getLimitableTR("shopMenuBG.png",mNormalTextureOption);
		}
		if(shopBG == null){
			shopBG = getLimitableTR("shopBG.png",mNormalTextureOption);
		}
		if(shopInfoBG == null){
			shopInfoBG = getLimitableTR("shopInfoBG.png",mNormalTextureOption);
		}
		if(shopInfoRowsBG == null){
			shopInfoRowsBG = getLimitableTTR("shopInfoRowsBG.png",1,2,mNormalTextureOption);
		}
		if(shopPropBG == null){
			shopPropBG = getLimitableTTR("shopPropBG.png",1,2,mNormalTextureOption);
		}
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
	}
	
	
	// ============================ 主题纹理  ================= //
	private void loadThemeTextures(){
		mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/theme/");
		if(homeTR == null){
			homeTR = getLimitableTR("home.png",mNormalTextureOption);
		}
		if(backTR == null){
			backTR = getLimitableTR("back.png",mNormalTextureOption);
		}
		if(theme1Temp == null){
			theme1Temp = getLimitableTR("theme1.png",mNormalTextureOption);
		}
		if(theme2Temp == null){
			theme2Temp = getLimitableTR("theme2.png",mNormalTextureOption);
		}
		if(themeBG == null){
			themeBG = getLimitableTR("themeBG.png",mNormalTextureOption);
		}
		if(bossBlackBG == null){
			bossBlackBG = getLimitableTTR("bossBlackBG.png",4,2,mNormalTextureOption);
		}
//		if(themeLevelBG == null){
//			themeLevelBG = getLimitableTR("themeLevelBG.png",mNormalTextureOption);
//		}
//		if(themeLevelLock == null){
//			themeLevelLock = getLimitableTR("themeLevelLock.png",mNormalTextureOption);
//		}
		//根据完成的关卡数来加载BOSS纹理
//		int themeSceneOneBossTotal = BangYouScreenActivity.getIntFromSharedPreferences(BangYouScreenActivity.SHARED_PREFS_THEME_1);
//		themeSceneOneBossTotalTT = new ArrayList<TiledTextureRegion>();
//		for(int i=0; i<themeSceneOneBossTotal+1; i++){
//			String bossTexture = "boss1" + i + ".png";
//			themeSceneOneBossTotalTT.add(getLimitableTTR(bossTexture,3,2,mNormalTextureOption));
//		}
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
	}
	
	private void loadThemeBoss(){
		//下面一行是测试代码,待删除
		//BangYouScreenActivity.writeIntToSharedPreferences(DataConstant.SHARED_PREFS_THEME_1, 7);
		
		
		//根据完成的关卡数来加载BOSS纹理
		mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/theme/");
		
		int themeSceneOneBossTotal = BangYouScreenActivity.getIntFromSharedPreferences(DataConstant.SHARED_PREFS_THEME_1);
		
		for(int i=0; i<themeSceneOneBossTotal+1; i++){
			if(mxdBoss_TTRArray[i] == null){
				String bossTexture = "boss1" + i + ".png";
				//String bossInfo = "infoboss1" + i + ".png";
				//mxdBoss_InfoTRArray[i] = getLimitableTR(bossInfo,mNormalTextureOption);
				switch (i) {
				case 0:
					mxdBoss_TTRArray[i] = getLimitableTTR(bossTexture,3,2,mNormalTextureOption);
					break;
				case 1:
					mxdBoss_TTRArray[i] = getLimitableTTR(bossTexture,3,2,mNormalTextureOption);
					break;
				case 2:
					mxdBoss_TTRArray[i] = getLimitableTTR(bossTexture,4,3,mNormalTextureOption);
					break;
				case 3:
					mxdBoss_TTRArray[i] = getLimitableTTR(bossTexture,4,2,mNormalTextureOption);
					break;
				case 4:
					mxdBoss_TTRArray[i] = getLimitableTTR(bossTexture,4,2,mNormalTextureOption);
					break;
				case 5:
					mxdBoss_TTRArray[i] = getLimitableTTR(bossTexture,3,2,mNormalTextureOption);
					break;
				case 6:
					mxdBoss_TTRArray[i] = getLimitableTTR(bossTexture,3,2,mNormalTextureOption);
					break;
				case 7:
					mxdBoss_TTRArray[i] = getLimitableTTR(bossTexture,3,1,mNormalTextureOption);
					break;
				}
			}
		}
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
	}
		
	// ============================ 游戏纹理  ================= //
	private void loadGameTextures(){
		mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
		if(greenButtonTTR == null){
			greenButtonTTR = getLimitableTTR("greenbutton.png",2,1,mNormalTextureOption);
		}
		if(redButtonTTR == null){
			redButtonTTR = getLimitableTTR("redbutton.png",2,1,mNormalTextureOption);
		}
		if(shareGameBG == null){
			shareGameBG = getLimitableTR("shareGameBG.jpg",mNormalTextureOption);
		}
		if(xue1 == null){
			xue1 = getLimitableTR("xue1.png",mNormalTextureOption);
		}
		if(xue2 == null){
			xue2 = getLimitableTR("xue2.png",mNormalTextureOption);
		}
		if(numberTTR == null){
			numberTTR = getLimitableTTR("number.png",12,1,mNormalTextureOption);
		}
		if(gamePauseBG == null){
			gamePauseBG = getLimitableTTR("gamePauseBG.png",1,2,mNormalTextureOption);
		}
		if(gamePauseMenu == null){
			gamePauseMenu = getLimitableTTR("gamePauseMenu.png",1,3,mNormalTextureOption);
		}
		if(layerTitle == null){
			layerTitle = getLimitableTTR("layerTitle.png",1,2,mNormalTextureOption);
		}
		if(bigBang == null){
			bigBang = getLimitableTTR("bigBang.png",3,3,mNormalTextureOption);
		}
		if(wuya == null){
			wuya = getLimitableTTR("wuya.png",2,2,mNormalTextureOption);
		}
		if(clockTime == null){
			clockTime = getLimitableTTR("clockTime.png",2,2,mNormalTextureOption);
		}
		if(iconCooling == null){
			iconCooling = getLimitableTTR("iconCooling.png",3,3,mNormalTextureOption);
		}
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
	}
	
	// ============================ 公共纹理  ================= //
	private void loadSharedTextures(){
		mPreviousAssetBasePath = BitmapTextureAtlasTextureRegionFactory.getAssetBasePath();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/shop/");
		if(weaponTTR == null){
			weaponTTR = getLimitableTTR("weapon.png",3,4,mNormalTextureOption);
		}
		if(magicTTR == null){
			magicTTR = getLimitableTTR("magic.png",3,4,mNormalTextureOption);
		}
		if(propsTTR == null){
			propsTTR = getLimitableTTR("props.png",2,2,mNormalTextureOption);
		}
		if(gameGold == null){
			gameGold = getLimitableTR("gold.png",mNormalTextureOption);
		}
		
		//未完待写
		for(int i=0; i<magicASTTRArray.length; i++){
			if(magicASTTRArray[i] == null){
				switch(i){
				case 0:
					magicASTTRArray[i] = getLimitableTTR("magic_as_0.png",3,4,mNormalTextureOption);
				break;
				case 1:
					magicASTTRArray[i] = getLimitableTTR("magic_as_11.png",3,4,mNormalTextureOption);
				break;
				}
			}
		}
		
		
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(mPreviousAssetBasePath);
	}
	
	// ============================ 字体  ================= //
	private static String DEFAULT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890~`!@#$%^&*()-_=+[] {};:'\",<.>?/\\";
	
	private void loadFonts(){
		if(mFont == null){
			mFont = FontFactory.createFromAsset(engine.getFontManager(), engine.getTextureManager(), 256, 256,activity.getAssets(),"fonts/Chunkfive.otf",32f,true,Color.WHITE_ABGR_PACKED_INT);
			mFont.load();
			mFont.prepareLetters(DEFAULT_CHARS.toCharArray());
		}
		if(sysFont == null){
			sysFont = FontFactory.create(engine.getFontManager(), engine.getTextureManager(), 256, 256,Typeface.create(Typeface.DEFAULT, Typeface.NORMAL),32f,true,Color.WHITE_ABGR_PACKED_INT);
			sysFont.load();
			sysFont.prepareLetters(DEFAULT_CHARS.toCharArray());
		}
	}
}
