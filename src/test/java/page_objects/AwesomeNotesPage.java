package page_objects;

import org.openqa.selenium.By;

import java.util.List;

public class AwesomeNotesPage extends BasePage{
    public static final String APP_URL = "http://localhost:3000/";

    private By notesListLocator = By.xpath("//*[@data-test-id='NOTE_LIST']");
    private By noteItemLocator = By.xpath("//*[@data-test-id='NOTE_ITEM']");
    private By noteItemTextLocator = By.xpath("//*[@data-test-id='NOTE_ITEM_TEXT']");
    private By noteItemDeleteLocator = By.xpath("//*[@data-test-id='NOTE_ITEM_DELETE_ACTION']");
    private By noteItemPriorityIconLocator = By.xpath("//*[@data-test-id='NOTE_ITEM_ICON_CONTAINER']");
    private By activeLanguageButtonLocator = By.xpath("//*[@data-test-id='SELECT_LANGUAGE']//div[contains(@class, 'MuiChip-colorSecondar')]");
    private By deleteConfirmationModalLocator = By.xpath("//*[@data-test-id='DELETE_NOTE_CONFIRMATION']");
    private By confirmModalChanges = By.xpath("//*[@data-test-id='DELETE_NOTE_CONFIRMATION_YES']");
    private By discardModalChanges = By.xpath("//*[@data-test-id='DELETE_NOTE_CONFIRMATION_NO']");
    private By addNoteButtonLocator = By.xpath("//*[@data-test-id='ADD_NOTE_SAVE_BUTTON']");
    private By addNoteInputLocator = By.xpath("//*[@data-test-id='ADD_NOTE_TEXT_INPUT']//input");
    private By newNotePriorityDropdownLocator = By.xpath("//*[@data-test-id='ADD_NOTE_PRIORITY_SELECT']");

    private By constructPriorityOptionByNameLocator(String option) {
        return By.xpath(String.format("//li[@role='option'][.='%s']", option));
    }

    private By constructLanguageSelectorLocator(String language) {
        return By.xpath(String.format("//*[contains(@data-test-id, 'SELECT_LANGUAGE_')][.='%s']", language));
    }

    private By constructDeleteNoteByPosLocator(int noteIndex) {
        return By.xpath(String.format("(//*[@data-test-id='NOTE_ITEM_DELETE_ACTION'])[%s]", noteIndex));
    }

    private By constructNoteItemTextByPosLocator(int noteIndex) {
        return By.xpath(String.format("(//*[@data-test-id='NOTE_ITEM_TEXT'])[%s]", noteIndex));
    }

    private By constructNotePriorityIconByPosLocator(int pos) {
        return By.xpath(String.format("(//*[@data-test-id='NOTE_ITEM_ICON_CONTAINER']//*[@title])[%s]", pos));
    }

    public void navigateToApp() {
        navigateTo(APP_URL);
    }

    public int getAvailableNotesNumber() {
        return getListOfElementsSize(noteItemLocator);
    }

    public List<String> getAvailableNotesText() {
        return getTextFromElements(noteItemTextLocator);
    }

    public boolean isPriorityIconDisplayed() {
        return isElementDisplayed(noteItemPriorityIconLocator);
    }

    public boolean isDeleteIconDisplayed() {
        return isElementDisplayed(noteItemDeleteLocator);
    }

    public String getCurrentLanguageSelected() {
        return getTextFromElement(activeLanguageButtonLocator);
    }

    public void changeApplicationLanguage(String applicationLanguage) {
        click(constructLanguageSelectorLocator(applicationLanguage));
    }

    public void pressNoteDeleteButtonByPos(int noteIndex) {
        click(constructDeleteNoteByPosLocator(noteIndex));
    }

    public boolean isConfirmationModalDisplayed() {
        return isElementDisplayed(deleteConfirmationModalLocator);
    }

    public void confirmModalChanges() {
        click(confirmModalChanges);
    }

    public void discardModalChanges() {
        click(discardModalChanges);
    }

    public String getNoteTextByPost(int noteIndex) {
        return getTextFromElement(constructNoteItemTextByPosLocator(noteIndex));
    }

    public boolean isAddNoteButtonDisplayed() {
        //TODO Debug this
        return driverFactory.getDriver().findElement(addNoteButtonLocator).isDisplayed() && driverFactory.getDriver().findElement(addNoteButtonLocator).isEnabled();
//        return isElementDisplayed(addNoteButtonLocator, 5);
    }

    public void scrollToAddNoteButton() {
        scrollIntoView(addNoteButtonLocator);
    }

    public void addTextIntoNewNoteInput(String text) {
        sendText(addNoteInputLocator, text);
    }

    public void addNoteButton() {
        click(addNoteButtonLocator);
    }

    public void selectNewNotePriority(String notePriority) {
        click(newNotePriorityDropdownLocator);
        click(constructPriorityOptionByNameLocator(notePriority));
    }

    public String getNotePriorityByPos(int addedNotePos) {
        return getAttributeValue(constructNotePriorityIconByPosLocator(addedNotePos), "title");
    }
}
