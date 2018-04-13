package yuvraj.midterm_project.ui.home.events;


import yuvraj.midterm_project.data.web.WebModel;

/**
 * when a webinfo needs to be created or updated
 */
public class OnWebUpdatedEvent {
	public final WebModel webModel;
	public final int webModelPosition;

	/**
	 *
	 * @param webModel Updated (or newly created) webinfo
	 * @param webModelPosition Position of the webinfo to be updated.
	 * If a new webinfo, just return a negative value, e.g. -1
	 */
	public OnWebUpdatedEvent(WebModel webModel, int webModelPosition) {
		this.webModel = webModel;
		this.webModelPosition = webModelPosition;
	}
}
