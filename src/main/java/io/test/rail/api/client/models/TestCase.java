package io.test.rail.api.client.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by alpa on 2018-12-06
 */
@Data
public class TestCase {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("section_id")
    @Expose
    private Integer sectionId;
    @SerializedName("template_id")
    @Expose
    private Integer templateId;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("priority_id")
    @Expose
    private Integer priorityId;
    @SerializedName("milestone_id")
    @Expose
    private Object milestoneId;
    @SerializedName("refs")
    @Expose
    private Object refs;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("created_on")
    @Expose
    private Integer createdOn;
    @SerializedName("updated_by")
    @Expose
    private Integer updatedBy;
    @SerializedName("updated_on")
    @Expose
    private Integer updatedOn;
    @SerializedName("estimate")
    @Expose
    private Object estimate;
    @SerializedName("estimate_forecast")
    @Expose
    private Object estimateForecast;
    @SerializedName("suite_id")
    @Expose
    private Integer suiteId;
    @SerializedName("custom_automation_type")
    @Expose
    private Integer customAutomationType;
    @SerializedName("custom_preconds")
    @Expose
    private Object customPreconds;
    @SerializedName("custom_steps")
    @Expose
    private Object customSteps;
    @SerializedName("custom_expected")
    @Expose
    private Object customExpected;
    @SerializedName("custom_steps_separated")
    @Expose
    private List<CustomStepsSeparated> customStepsSeparated = null;
    @SerializedName("custom_mission")
    @Expose
    private Object customMission;
    @SerializedName("custom_goals")
    @Expose
    private Object customGoals;
}
