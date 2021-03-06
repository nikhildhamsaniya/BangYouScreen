package org.game.bangyouscreen.layer;


import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.game.bangyouscreen.managers.ManagedLayer;
import org.game.bangyouscreen.managers.ResourceManager;
import org.game.bangyouscreen.managers.SFXManager;
import org.game.bangyouscreen.managers.SceneManager;
import org.game.bangyouscreen.scene.FingerScene;
import org.game.bangyouscreen.scene.MainMenuScene;
import org.game.bangyouscreen.util.EntityUtil;

public class FingerPauseLayer extends ManagedLayer{
	
	private static final FingerPauseLayer INSTANCE = new FingerPauseLayer();
	//private static int themeNum = 0;
	private float mCameraWidth = ResourceManager.getCamera().getWidth();
	private float mCameraHeight = ResourceManager.getCamera().getHeight();
	private VertexBufferObjectManager mVertexBufferObjectManager = ResourceManager.getEngine().getVertexBufferObjectManager();
	private Sprite LayerBG;
	
	public static FingerPauseLayer getInstance() {
		//themeNum = theme;
		return INSTANCE;
	}
	
	IUpdateHandler mSlideInUpdateHandler = new IUpdateHandler() {
		
		@Override
		public void onUpdate(final float pSecondsElapsed) {
			if (LayerBG.getY() > (mCameraHeight / 2f)) {
				LayerBG.setY(Math.max(LayerBG.getY() - (pSecondsElapsed * mSLIDE_PIXELS_PER_SECONDS),0f));
			} else {
				LayerBG.setY(mCameraHeight / 2f);
				unregisterUpdateHandler(this);
			}
		}

		@Override
		public void reset() {}
	};

	IUpdateHandler mSlideOutUpdateHandler = new IUpdateHandler() {
		@Override
		public void onUpdate(final float pSecondsElapsed) {
			if (LayerBG.getY() < ((mCameraHeight / 2f) + (LayerBG.getHeight() / 2f))) {
				LayerBG.setY(Math.min(LayerBG.getY() + (pSecondsElapsed * mSLIDE_PIXELS_PER_SECONDS),
								(mCameraHeight / 2f) + (LayerBG.getHeight() / 2f)));
			} else {
				unregisterUpdateHandler(this);
				SceneManager.getInstance().hideLayer();
			}
		}

		@Override
		public void reset() {}
	};

	@Override
	public void onLoadLayer() {
		//父背景变成半透明
		final Rectangle fadableBGRect = new Rectangle(0f, 0f,mCameraWidth,mCameraHeight, mVertexBufferObjectManager);
		fadableBGRect.setPosition(mCameraWidth/2f, mCameraHeight/2f);
		fadableBGRect.setColor(0f, 0f, 0f, 0.6f);
		attachChild(fadableBGRect);
		
		LayerBG = new Sprite(0f, 0f,ResourceManager.layerPauseBG, mVertexBufferObjectManager);
		//LayerBG.setSize(mCameraWidth/6f, mCameraHeight*(2f/3f));
		EntityUtil.setSize("height", 2f/3f, LayerBG);
		LayerBG.setPosition(mCameraWidth/2f, (mCameraHeight / 2f) + (ResourceManager.loadingBG.getHeight() / 2f));
		attachChild(LayerBG);
		
		//重新开始
		ButtonSprite restartBS = new ButtonSprite(0f,0f,ResourceManager.gamePauseMenu.getTextureRegion(1),mVertexBufferObjectManager);
		restartBS.setPosition(LayerBG.getWidth()/2f, LayerBG.getHeight()/2f);
		EntityUtil.setSizeInParent("width", 4f/5f, restartBS, LayerBG);
		restartBS.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
					SFXManager.getInstance().playSound("a_click");
					onHideLayer();
					SceneManager.getInstance().showScene(new FingerScene());
			}});
		LayerBG.attachChild(restartBS);
		registerTouchArea(restartBS);
		
		//继续游戏
		ButtonSprite continueBS = new ButtonSprite(0f,0f,ResourceManager.gamePauseMenu.getTextureRegion(0),mVertexBufferObjectManager);
		continueBS.setPosition(restartBS.getX(), LayerBG.getHeight()*(5f/6f));
		continueBS.setSize(restartBS.getWidth(), restartBS.getHeight());
		continueBS.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
					SFXManager.getInstance().playSound("a_click");
					onHideLayer();
					((FingerScene) SceneManager.getInstance().mCurrentScene).onResumeGameLevel();
			}});
		LayerBG.attachChild(continueBS);
		registerTouchArea(continueBS);
		
		//返回菜单
		ButtonSprite goBackBS = new ButtonSprite(0f,0f,ResourceManager.gamePauseMenu.getTextureRegion(2),mVertexBufferObjectManager);
		goBackBS.setPosition(restartBS.getX(), LayerBG.getHeight()/6f);
		goBackBS.setSize(restartBS.getWidth(), restartBS.getHeight());
		goBackBS.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
					SFXManager.getInstance().playSound("a_click");
					onHideLayer();
					SceneManager.getInstance().showScene(MainMenuScene.getInstance());
			}});
		LayerBG.attachChild(goBackBS);
		registerTouchArea(goBackBS);
		
		//AppConnect.getInstance(ResourceManager.getInstance().activity).showPopAd(ResourceManager.getInstance().activity);
		//View popAdSprite = AppConnect.getInstance(ResourceManager.getInstance().activity).getPopAdView(ResourceManager.getInstance().activity);
		//ResourceManager.getInstance().loadNewAd();
		
	}
	
	@Override
	public void onShowLayer() {
		registerUpdateHandler(mSlideInUpdateHandler);
		//ResourceManager.getInstance().showPopAd();
	}
	
	@Override
	public void onHideLayer() {
		registerUpdateHandler(mSlideOutUpdateHandler);
	}

	@Override
	public void onUnloadLayer() {
		// TODO Auto-generated method stub
		
	}

}
