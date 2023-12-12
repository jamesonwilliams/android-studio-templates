package org.nosemaj.astemplates

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

class AndroidStudioTemplateProvider : WizardTemplateProvider() {
  override fun getTemplates(): List<Template> {
    return listOf(projectTemplate)
  }
}
