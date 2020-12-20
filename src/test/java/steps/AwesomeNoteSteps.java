package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import page_objects.AwesomeNotesPage;

public class AwesomeNoteSteps {
    private final AwesomeNotesPage page = new AwesomeNotesPage();

    private String noteText;

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    @Given("user navigates to application address")
    public void userNavigatesToApplicationAddress() {
        page.navigateToApp();
    }

    @And("note has priority icon")
    public void noteHasPriorityIcon() {
        Assert.assertTrue("Note priority is missing!", page.isPriorityIconDisplayed());
    }

    @Then("a list of available should be displayed")
    public void aListOfAvailableShouldBeDisplayed() {
        Assert.assertTrue("Notes list is empty!",page.getAvailableNotesNumber() > 0);
    }

    @And("note has text")
    public void noteHasText() {
        page.getAvailableNotesText()
                .forEach(text -> Assert.assertFalse("Found note item with no text!", text.isEmpty()));
    }

    @And("note has delete button")
    public void noteHasDeleteButton() {
        Assert.assertTrue("Delete icon is missing from note item!", page.isDeleteIconDisplayed());
    }

    @Then("language selected should be {string}")
    public void languageSelectedShouldBe(String applicationLanguage) {
        Assert.assertEquals(String.format("Application language is not '%s'!", applicationLanguage), page.getCurrentLanguageSelected(), applicationLanguage);
    }

    @When("user changes application language to {string}")
    public void userChangesApplicationLanguageTo(String applicationLanguage) {
        page.changeApplicationLanguage(applicationLanguage);
    }

    @When("user selects delete icon for {int} note")
    public void userSelectsDeleteIconForNote(int noteIndex) {
        setNoteText(page.getNoteTextByPost(noteIndex));

        page.pressNoteDeleteButtonByPos(noteIndex);
    }

    @Then("a confirmation modal for delete should be displayed")
    public void aConfirmationModalForDeleteShouldBeDisplayed() {
        Assert.assertTrue("Delete modal not present!", page.isConfirmationModalDisplayed());
    }

    @When("user confirms changes displayed in modal")
    public void userConfirmsChangesDisplayedInModal() {
        page.confirmModalChanges();
    }

    @Then("previously deleted note should not be available")
    public void previouslyDeletedNoteShouldNotBeAvailable() {
        Assert.assertFalse("Previously deleted note info was not saved!", getNoteText().isEmpty());

        Assert.assertFalse("Deleted note still in list!", page.getAvailableNotesText().contains(getNoteText()));
    }

    @When("user discards changes displayed in modal")
    public void userDiscardsChangesDisplayedInModal() {
        page.discardModalChanges();
    }

    @Then("previously attempted note to delete should be available")
    public void previouslyAttemptedNoteToDeleteShouldBeAvailable() {
        Assert.assertFalse("Previously attempted deleted note info was not saved!", getNoteText().isEmpty());

        Assert.assertTrue("Note was removed on discard!", page.getAvailableNotesText().contains(getNoteText()));
    }

    @When("user resizes browser to {int} width and {int} height")
    public void userResizesBrowserToWidthAndHeight(int width, int height) {
        page.setBrowserResolution(width, height);
    }

    @Then("verify the add note button is not visible")
    public void verifyTheAddNoteButtonIsNotVisible() {
        Assert.assertFalse("Add note button is still visible!", page.isAddNoteButtonDisplayed());
    }

    @When("user scrolls down to the add button location")
    public void userScrollsDownToTheAddButtonLocation() {
        page.scrollToAddNoteButton();
    }

    @Then("verify the add note button is visible")
    public void verifyTheAddNoteButtonIsVisible() {
        Assert.assertTrue("Add note button is not visible!", page.isAddNoteButtonDisplayed());
    }

    @When("user adds text {string} into new note input field")
    public void userAddsTextIntoNewNoteInputField(String noteText) {
        setNoteText(noteText);

        page.addTextIntoNewNoteInput(noteText);
    }

    @And("user clicks add note button")
    public void userClicksAddNoteButton() {
        page.addNoteButton();
    }

    @Then("added note should be displayed in the list")
    public void addedNoteShouldBeDisplayedInTheList() {
        Assert.assertFalse("Added note info was not saved!", getNoteText().isEmpty());

        Assert.assertTrue("Note list does not contain previously added note!", page.getAvailableNotesText().contains(getNoteText()));
    }

    @And("user selects {string} note priority")
    public void userSelectsNotePriority(String notePriority) {
        page.selectNewNotePriority(notePriority);
    }

    @And("added note should have {string} priority")
    public void addedNoteShouldHavePriority(String expectedPriority) {
        int addedNotePost = page.getAvailableNotesText().indexOf(getNoteText()) + 1;

        Assert.assertTrue("Added note info was not found!", addedNotePost >= 0);

        Assert.assertEquals("Added note priority does not match!", expectedPriority, page.getNotePriorityByPos(addedNotePost));
    }
}
