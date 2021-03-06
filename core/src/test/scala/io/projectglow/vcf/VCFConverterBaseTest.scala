/*
 * Copyright 2019 The Glow Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.projectglow.vcf

import scala.reflect.runtime.universe._

import io.projectglow.common.{GenotypeFields, TestUtils, VCFRow}

trait VCFConverterBaseTest extends TestUtils {

  final lazy val defaultContigName = ""
  final lazy val defaultStart = 0L
  final lazy val defaultEnd = 0L
  final lazy val defaultNames = Seq.empty
  final lazy val defaultReferenceAllele = ""
  final lazy val defaultAlternateAlleles = Seq.empty
  final lazy val defaultQual = None

  final val defaultVcfRow = VCFRow(
    contigName = defaultContigName,
    start = defaultStart,
    end = defaultEnd,
    names = defaultNames,
    referenceAllele = defaultReferenceAllele,
    alternateAlleles = defaultAlternateAlleles,
    qual = defaultQual,
    filters = Seq.empty,
    attributes = Map.empty,
    genotypes = Seq(defaultGenotypeFields)
  )

  final lazy val defaultGenotypeFields = GenotypeFields(
    sampleId = None,
    phased = None,
    calls = None,
    depth = None,
    filters = None,
    genotypeLikelihoods = None,
    phredLikelihoods = None,
    posteriorProbabilities = None,
    conditionalQuality = None,
    haplotypeQualities = None,
    expectedAlleleCounts = None,
    mappingQuality = None,
    alleleDepths = None,
    otherFields = Map.empty
  )

  final lazy val defaultAlleles = Seq(defaultReferenceAllele) ++ defaultAlternateAlleles

  final lazy val defaultAlternateAllele = None
  final lazy val defaultAlleleIdx = None
  final lazy val defaultNonRefAlleleIdx = None
  final lazy val defaultSplitFromMultiAllelic = false

  protected def getClassFields[T: TypeTag]: Seq[String] = {
    typeOf[T].members.sorted.collect {
      case m: MethodSymbol if m.isParamAccessor => m.name.toString
    }
  }
}
