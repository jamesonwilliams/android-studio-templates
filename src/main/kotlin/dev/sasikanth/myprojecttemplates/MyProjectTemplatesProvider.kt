package dev.sasikanth.myprojecttemplates

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

class MyProjectTemplatesProvider : WizardTemplateProvider() {
  override fun getTemplates(): List<Template> {
    return listOf(projectTemplate)
  }
}
